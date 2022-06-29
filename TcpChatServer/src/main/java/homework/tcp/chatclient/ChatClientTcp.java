package homework.tcp.chatclient;


import homework.model.File;
import homework.tcp.chatserver.ChatServerTcp;
import homework.dao.impl.JdbiQueryMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j

public class ChatClientTcp implements ChatClient, Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ConnectionSettings settings;
    private InetAddress address;
    private volatile boolean canceled = false;
    private List<MessageListener> listeners = new CopyOnWriteArrayList<>();




    protected void cancel() {
        canceled = true;
    }

    @Override
    public String login(ConnectionSettings settings ) {
        this.settings = settings;
        try {
            address = InetAddress.getByName(settings.getHost());
            socket = new Socket(address, settings.getPort());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            out.println(settings.getNickname());
            log.info("User {} successfully logged in to server: {}", settings.getNickname(), socket);
            return null;
        } catch (UnknownHostException e) {
            log.error("Unknown chat server hostname:", e);
            return "Unknown chat server hostname: " + settings.getHost();
        } catch (IOException e) {
            log.error("Error connecting to chat server:", e);
            return "Error connecting to chat server: " + address + ":" + settings.getPort();
        }
    }

    @Override
    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public boolean logout() {
        out.println("<END>");
        cancel();
        try {
            in.close();
            out.close();
            socket.close();
            return true;
        } catch (IOException e) {
            log.error("Error closing chat client:", e);
            return false;
        }
    }

    @Override
    public void addMessageListener(MessageListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeMessageListener(MessageListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void run() {
        while (!canceled && !Thread.interrupted()) {
            try {
                String message = in.readLine();
                if (!canceled && !Thread.interrupted() && message != null)
                    for (var listener : listeners) {
                        if (message.contains(".txt upload")){
                            var mesage = message.replace(" upload", "").trim();
                            readFileAndSplit(mesage);
                        }
                        listener.onMessage(message);
}

            } catch (IOException e) {
                log.error("Error receiving messge from server:", e);
                for (var listener : listeners) {
                    listener.onError("Error receiving messge from server:" + e.getMessage());
                }
            }
        }
        log.info("Chat client stopped: {}", settings);
    }

    private void readFileAndSplit(String message) throws IOException {
        String myTextFile = "files/" + message;
        Path myPath = Paths.get(myTextFile.trim());
        //List<String> strArray = new ArrayList<>();
       var strArray = Files.lines(myPath)
                .map(s -> Arrays.toString(s.split(",")))
               .collect(Collectors.toList());

        var name = strArray.get(0).replaceAll("\\[", "").replaceAll("\\]","").trim();
        var version = strArray.get(1).replaceAll("\\[", "").replaceAll("\\]","").trim();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 2; i < strArray.size() ; i++) {
            stringBuilder.append(strArray.get(i)).append(System.lineSeparator());
        }
        var content = stringBuilder.toString().replaceFirst("\\[", "").trim();
        File file = new File(name,version,content);
        JdbiQueryMethods jdbiQueryMethods = new JdbiQueryMethods();
        jdbiQueryMethods.uploadFileToDatabase(file);
    }

    private static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(30, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(30, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        JdbiQueryMethods jdbiQueryMethods = new JdbiQueryMethods();
        Scanner sc = new Scanner(System.in);
        String nickname = "";
        jdbiQueryMethods.users.forEach(System.out::println);
        while (!jdbiQueryMethods.users.isEmpty()) {

            System.out.print("Enter your nickname:");
            nickname = sc.nextLine();
            System.out.print("Enter your pass:");
            String pass = sc.nextLine();
            if (jdbiQueryMethods.returnUserValid(nickname, pass)){
                log.info("Successfully");
                break;
            }else {
                log.error("Wrong data try again");
            }
        }


        // create client
        var settings = new ConnectionSettings("localhost", ChatServerTcp.PORT, nickname);
        var client = new ChatClientTcp();

        // add message listener to listen for incoming message
        client.addMessageListener(new MessageListener() {
            @Override
            public void onMessage(String message) {
                System.out.println("::: " + message);
            }

            @Override
            public void onError(String error) {
                System.out.println("!!! " + error);
            }
        });

        // login client -> in, out initilaized
        var errorMessage = client.login(settings);
        if (errorMessage != null) {
            System.out.println(errorMessage);
        } else {
            System.out.println("Successfully connected to the chat server: " + settings);
        }

        // run method in separate thread -> send message to MessageListeners
        var executor = Executors.newCachedThreadPool();
        executor.execute(client);
        System.out.print(">");

        // read messages from console and send them to chat server
        String message = "";
        while (!message.equals("quit")) {
            message = sc.nextLine();
            client.sendMessage(message);
        }

        // close client
        client.logout();
        shutdownAndAwaitTermination(executor);
    }
}

