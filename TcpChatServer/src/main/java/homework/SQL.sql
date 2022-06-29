create
database if not exists tcp_file_comparison;
use
tcp_file_comparison;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`               bigint       NOT NULL AUTO_INCREMENT,
    `username`         varchar(255)  NOT NULL,
    `password`         varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UC_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `file`;

CREATE TABLE `file`
(
    `id`                 bigint        NOT NULL AUTO_INCREMENT,
    `fileName`             varchar(255)        NOT NULL,
    `version`               varchar(80)   NOT NULL,
    `fileContent`   varchar(10000)  NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO user (`username`, `password`)
VALUES ('georgi', 'georgi123');


INSERT INTO file (`fileName`, `version`, `fileContent`)
VALUES ('myfile.txt', '1.1', 'try {
            address = InetAddress.getByName(settings.getHost());
            socket = new Socket(address, settings.getPort());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
');
INSERT INTO file (`fileName`, `version`, `fileContent`)
VALUES ('myfile.txt', '1.5', 'try {
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
        }');
INSERT INTO file (`fileName`, `version`, `fileContent`)
VALUES ('test.txt', '1.1', ' public boolean logout() {
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
    }');

