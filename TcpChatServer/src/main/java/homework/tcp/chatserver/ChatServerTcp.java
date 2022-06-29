package homework.tcp.chatserver;


import homework.model.File;
import homework.dao.impl.JdbiQueryMethods;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
class Handler implements Runnable {
    private ChatServerTcp server;
    private final Socket  socket;
    private BufferedReader in;
    private PrintWriter out;
    private String nickname;


    public Handler(ChatServerTcp server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            JdbiQueryMethods jdbiQueryMethods = new JdbiQueryMethods();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            String message = "";
            String file = "";
            String version = "";
            String fileAndVersion ;
            // chat application protocol: 1) read nickname as first message
            nickname = in.readLine();
            log.info("User {} logged in.", nickname);

            // 2) Read chat messages until <END> message is received
            var finished = false;
            while(!finished && !Thread.interrupted()) {
                // read request

                message = in.readLine();

                if (message.contains("//getfile ")){
                    fileAndVersion = message.replace("//getfile ","");
                   var fileArr = fileAndVersion.split(",\\s+");
                   file = fileArr[0];
                   version = fileArr[1];
                    makeTheFile(file, version,jdbiQueryMethods);

                    log.info("File created in files/fromserver folder. File name: {} File version: {}", file,version);
                }
                if (message.contains("//getfilelatest ")){
                    fileAndVersion = message.replace("//getfilelatest ","");
                    var fileArr = fileAndVersion;
                    
                    makeTheFileLatest(fileArr ,jdbiQueryMethods);

                    log.info("File created in files/fromserver folder. File name: {} File version: {}", file,version);
                }


                log.info("Message received: " + String.format("%s: %s",nickname, message));
                if (message.equals("<END>")) {
                    finished = true;
                    continue;
                }
                if(!message.isBlank()) {
                    server.sendToAll(String.format("%s ", message));
                }

            }

            // 3) Close session when <END> is received
            log.info("Closing session for user: {}", nickname);
            in.close();
            out.close();
            socket.close();
            server.removeHandler(this);
        } catch (IOException e) {
            log.error("Error reading/writing from/to TCP socket:", e);
            throw new RuntimeException(e);
        }
    }

    private void makeTheFileLatest(String fileArr, JdbiQueryMethods jdbiQueryMethods) throws IOException {
        File myfile;
        if (!fileArr.isEmpty()) {
            myfile = jdbiQueryMethods.downloadFileFromDatabaseLatest(fileArr);

            var fileName = myfile.getFileName();
            var fileVersion = myfile.getVersion();
            var fileContent = myfile.getFileContent();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(fileName).append(System.lineSeparator())
                    .append(fileVersion).append(System.lineSeparator())
                    .append(fileContent);
            var fileNameDir = "files/fromserver/" + fileName;
            var stringFile = stringBuilder.toString();

            Files.writeString(Paths.get(fileNameDir), stringFile, StandardCharsets.ISO_8859_1);
        }
    }

    private synchronized void makeTheFile(String file, String version, JdbiQueryMethods jdbiQueryMethods) throws IOException {
        File myfile;
        if (!file.isEmpty() && !version.isEmpty()) {
            myfile  = jdbiQueryMethods.downloadFileFromDatabase(file, version);

            var fileName = myfile.getFileName();
            var fileVersion = myfile.getVersion();
            var fileContent = myfile.getFileContent();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(fileName).append(System.lineSeparator())
                    .append(fileVersion).append(System.lineSeparator())
                    .append(fileContent);
            var fileNameDir = "files/fromserver/" + fileName;
            var stringFile = stringBuilder.toString();

            Files.writeString(Paths.get(fileNameDir),stringFile, StandardCharsets.ISO_8859_1);
        }
    }

    public void sendMessage(String message) {

        out.println(message);
    }
}

@Slf4j
public class ChatServerTcp implements Runnable {
    public static final int PORT = 9090;

    private volatile boolean canceled = false;

    private ExecutorService executor = Executors.newCachedThreadPool();
    private Collection<Handler> handlers = new CopyOnWriteArrayList<>();
//    private Collection<Handler> handlers = new ConcurrentSkipListSet<>();

    public void cancel() {
        this.canceled = true;
    }

    @Override
    public void run() {
        try(ServerSocket ssoc = new ServerSocket(PORT, -1,
                InetAddress.getByAddress(new byte[] {127, 0, 0, 1}))) {
            log.info("Chat Server is listening for connections on: {}", ssoc);
            while(!canceled && !Thread.interrupted()) {
                try {
                    Socket s = ssoc.accept();
                    log.info("Time Server connection accepted: {}", s);
                    var handler = new Handler(this, s);
                    handlers.add(handler);
                    executor.execute(handler);
                }catch (IOException e) {
                    log.error("Error accepting client connection:", e);
                }
            }
            log.info("Closing Chat Server.");
        } catch (IOException e) {
            log.error("Error running Chat Server:", e);
            throw new RuntimeException(e);
        } finally {
            executor.shutdownNow();
        }
    }

    public void sendToAll(String message) {
        for(var handler: handlers){
            handler.sendMessage(message);
        }
    }

    public void removeHandler(Handler handler) {
        handlers.remove(handler);
    }

    public static void main(String[] args) {

        /**
         * Има още няколко неща които исках да оправя но не ми стигна времето.
         * SQL файл има мокнати данни и база данни
         * разбрах че само .txt файл да използваме,
         * но ако грешно съм разбрал лесно се мигрира към каквъто и да е файл
         * Има команди:
         * команда //getfile (име на файл.txt), (версия) - пример: //getfile myfile.txt, 1.5
         * команда //getfilelatest (име на файл.txt) - пример: //getfilelatest myfile.txt
         * за да качим файл трябва само да напишем името на файл и после upload
         * пример: myfile.txt upload  -> ((message.contains(".txt upload")))
         *
         * за да качим файл файлт трябва да е в директория files в проекта ,а свалените файлове отиват в директория fails/fromserver
         *
         * Задачата е много интересна и приятна но за толкова време само това можех да измисля.
         * за бъдеще мисля да я развия доста задачката понеже наистина много ми хареса като идея.
         *
         * Структората на файловете е следната:
         * име на файл
         * номер на версия
         * контекст
         *
         * Програмата чете по-оделно първи и втори ред всичко друго го взима като едно
         * може да се използва и като анонимен чат и само сървъра да вижда кой е написал дадено съобщение.
         */
        new ChatServerTcp().run();
    }


}
