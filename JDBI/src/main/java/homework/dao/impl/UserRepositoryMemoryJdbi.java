package homework.dao.impl;

import homework.dao.UserRepositoryJdbi;
import homework.model.User;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryMemoryJdbi implements UserRepositoryJdbi {
    public static final String USERS_TABLE = "user";
    public static final String CREATE_USER = "INSERT INTO `" + USERS_TABLE + "` " +
            " (`name`, `username`, `password`, `gender`, `role`, `url`, `shortDescription`, `status`) " +
            " VALUES (:name, :username, :password, :gender, :role, :url, :shortDescription, :status) ";

    public static final String UPDATE_USER = "UPDATE `" + USERS_TABLE + "` " +
            " SET `name` = :name, `username` = :username, `password` = :password, `gender` = :gender, " +
            "`role` = :role , `url` = :url , " +
            "`shortDescription` = :shortDescription , `status` = :status" +
            " WHERE id = :id;";

    public static final String FIND_BY_ID = "SELECT * FROM `" + USERS_TABLE + "` WHERE `id` = :id;";
    public static final String FIND_BY_USERNAME = "SELECT * FROM `" + USERS_TABLE + "` WHERE `username` = :username;";
    public static final String FIND_ALL = "SELECT * FROM `" + USERS_TABLE + "` ORDER BY `username` ; ";
    private static final String DELETE_USER_BY_ID = "delete from user where `id` = :id; ";
    private Jdbi jdbi;


    @Autowired
    public UserRepositoryMemoryJdbi(Jdbi jdbi) {
        this.jdbi = jdbi;
       // this.jdbi.registerRowMapper(new User.UserMapper());
    }


//    @GetGeneratedKeys("id")
//    public User create(User user) {
//        return  this.jdbi.withHandle(handle -> handle.createUpdate(CREATE_USER)
//                .bind("name", user.getName())
//                .bind("username", user.getUsername())
//                .bind("password", user.getPassword())
//                .bind("gender",user.getGender())
//                .bind("role", user.getRole())
//                .bind("url", user.getUrl())
//                .bind("shortDescription", user.getShortDescription())
//                .bind("status", user.getStatus())
//                .bind("created", user.getCreated())
//                .bind("modified", user.getModified())
//                .executeAndReturnGeneratedKeys()
//                .mapTo(User.class)
//                .findFirst().orElse(null)
//
//        );
//    }

//
//    @GetGeneratedKeys
//    public User create(User user) {
//        if (this.jdbi.withHandle(handle -> handle.createUpdate(CREATE_USER)
//                .bindBean(user)
//
//                .execute()) > 0) return new User(user.getId(), user.getName(), user.getUsername(), user.getPassword(), user.getGender(),user.getRole(),user.getUrl(), user.getShortDescription(), user.getStatus(), user.getCreated(),user.getModified());
//        return user;
//    }

//    public User create(User entity) {
//
//        try (Handle handle = this.jdbi.open()) {
//
//            Long id = handle.createUpdate(CREATE_USER)
//                    .bindBean(entity)
//                    .executeAndReturnGeneratedKeys("id")
//                    .mapTo(Long.class)
//                    .one();
//
//            entity.setId(id)
//            ;
//
//            return entity;
//        }
//    }

//@GetGeneratedKeys("id")
//    public User create(User user) {
//        return (User) this.jdbi.withHandle(handle -> handle.createUpdate(CREATE_USER)
//                .bind("id", user.getId())
//                .bind("name", user.getName())
//                .bind("username", user.getUsername())
//                .bind("password", user.getPassword())
//                .bind("gender",user.getGender())
//                .bind("role", user.getRole())
//                .bind("url", user.getUrl())
//                .bind("shortDescription", user.getShortDescription())
//                .bind("status", user.getStatus())
//                .bind("created",user.getCreated())
//                .bind("modified",user.getModified())
//                .executeAndReturnGeneratedKeys("id")
//                .map(new User.UserMapper())
//                .list());
//
//    }
//@GetGeneratedKeys("id")
//public Integer create(User entity) {
//    var hoho = this.jdbi.withHandle(handle -> handle.createUpdate(CREATE_USER)
//            .bindBean(entity)
//            .execute());
//    return hoho;
//}


//    @RegisterBeanMapper(User.class)
//    public User create(@BindBean User user) {
//        return jdbi.withHandle(handle ->
//                handle.createUpdate(CREATE_USER)
//                        .bindBean(user)
//                        .executeAndReturnGeneratedKeys()
//                        .mapTo(User.class)
//                        .one());
//    }
    public User create(User user) {
     var id = this.jdbi.withHandle(handle -> handle.createUpdate(CREATE_USER)
             .bindBean(user)
             .executeAndReturnGeneratedKeys("id")
             .mapTo(Long.class)
             .one());
     user.setId(id);
     return user;

    }

    public Optional<User> update(User user) {
    this.jdbi.withHandle(handle -> handle.createUpdate(UPDATE_USER)
            .bindBean(user)
            .execute());
    return Optional.ofNullable(findById(user.getId()));
    }
    public User findById(Long id) {
      return this.jdbi.withHandle(handle -> handle.createQuery(FIND_BY_ID)
              .bind("id",id)
              .mapToBean(User.class)
              .one());
    }
    public User findByUsername(String username) {
      return this.jdbi.withHandle(handle -> handle.createQuery(FIND_BY_USERNAME)
              .bind("username",username)
              .mapToBean(User.class)
              .one());
    }

    public List<User> findAll() {
    return this.jdbi.withHandle(handle -> handle.createQuery(FIND_ALL)
            .mapToBean(User.class)
            .list());
    }
    public boolean deleteById(Long id) {
        return this.jdbi.withHandle(handle ->
                handle.createUpdate(DELETE_USER_BY_ID)
                        .bind("id", id)
                        .execute()) > 0 ? true : false;
    }
}
