package homework.dao.impl;

import homework.dao.JdbiQueryDao;
import homework.model.File;
import homework.model.User;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class JdbiQueryMethods implements JdbiQueryDao {


    final String USERS_TABLE = "user";

    final String FIND_BY_USERNAME = "SELECT * FROM `" + USERS_TABLE + "` WHERE `username` = :username AND `password` = :password;";

    final String FILE_TABLE = "file";
    final String FIND_OUR_FILE = "SELECT * FROM `file` WHERE `fileName` = :fileName AND `version` = :version;";
    final String FIND_OUR_FILE_LATEST = "SELECT * FROM `file` WHERE `fileName` = :fileName ORDER BY `version` DESC ;";
    final String CREATE_FILE = "INSERT INTO `" + FILE_TABLE + "` " +
            " (`fileName`, `version`, `fileContent`) " +
            " VALUES (:fileName, :version, :fileContent) ";

    // public Jdbi jdbi = Jdbi.create("jdbc:mysql", "root", "root");
    public Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/tcp_file_comparison?useUnicode=true&createDatabaseIfNotExist=true&autoReconnect=true", "root", "root");
    public List<User> users = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM `user` ORDER BY `username`")
            .mapToBean(User.class)
            .list());


    public String findByUsernameAndPassword(String username, String password) {
        var user = jdbi.withHandle(handle -> handle.createQuery(FIND_BY_USERNAME)
                .bind("username", username)
                .bind("password", password)
                .mapToBean(User.class)
                .findOne().orElseThrow(() -> new RuntimeException("opitaj pak")));
        return user.getUsername();
    }

    public void uploadFileToDatabase(File file) {
        var id = this.jdbi.withHandle(handle -> handle.createUpdate(CREATE_FILE)
                .bindBean(file)
                .executeAndReturnGeneratedKeys("id")
                .mapTo(Long.class)
                .one());
        file.setId(id);

    }
    public boolean returnUserValid(String username, String password) {
        try (Handle handle = jdbi.open()) {
            var sql = handle.createQuery(FIND_BY_USERNAME)
                    .bind("username", username)
                    .bind("password", password)
                    .mapToBean(User.class)
                    .findOne();
            if (sql.isPresent()) {
                return true;
            } else return false;
        }
    }

    public File downloadFileFromDatabase(String fileName, String version) {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery(FIND_OUR_FILE)
                    .bind("fileName", fileName)
                    .bind("version", version)
                    .mapToBean(File.class)
                    .findOne().orElseThrow(() -> new RuntimeException("opitaj pak"));
        }
//        return jdbi.withHandle(handle -> handle.createQuery(FIND_OUR_FILE))
//                .bind("fileName",fileName)
//                .bindBean("version", version)
//                .mapToBean(File.class)
//                .findOne().orElseThrow(() -> new RuntimeException("opitaj pak"));
    }

    public File downloadFileFromDatabaseLatest(String fileName) {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery(FIND_OUR_FILE_LATEST)
                    .bind("fileName", fileName)
                    .mapToBean(File.class)
                    .findFirst().orElseThrow(() -> new RuntimeException("opitaj pak"));
        }
    }
}

