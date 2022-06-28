package homework.dao.impl;


import homework.dao.UserRepositoryJdbc;
import homework.exception.PersistenceException;
import homework.model.User;
import homework.util.JdbcUtil;
import org.springframework.stereotype.Repository;


import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJdbcImpl extends AbsRepositoryUserImpl implements UserRepositoryJdbc {
    private static final String FIND_USER_BY_USERNAME = "select * from user where username = ?; ";
    private static final String UPDATE_USER = "update user " +
            "set `name`=?,`username`=?, `password`=?, `gender`=?, `role`=?, `url`=?, `shortDescription`=?, `status`=? " +
            "where id = ?; ";

    private static final String INSERT_NEW_USER = "INSERT INTO user " +
            "(`name`, `username`, `password`, `gender`, `role`, `url`, `shortDescription`, `status`, `created`, `modified`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";



    public UserRepositoryJdbcImpl(Connection connection) {
        super(connection);
    }

    protected Connection getConnection() {
        return super.getConnection();
    }



    @Override
    public User create(User user) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getGender().name());
            preparedStatement.setString(5, user.getRole().name());
            preparedStatement.setString(6, user.getUrl());
            preparedStatement.setString(7, user.getShortDescription());
            preparedStatement.setString(8, user.getStatus().name());
            preparedStatement.setTimestamp(9, Timestamp.valueOf(user.getCreated()));
            preparedStatement.setTimestamp(10, Timestamp.valueOf(user.getModified()));

            int numRecords = preparedStatement.executeUpdate();

            if (numRecords > 0) {
                ResultSet keys = preparedStatement.getGeneratedKeys();
                try {
                    keys.next();
                    user.setId(keys.getLong(1));
                    return user;
                } catch (SQLException e) {
                    throw new PersistenceException("Error fetching generated key for User: " + user.getUsername(), e);
                }
            }
            throw new PersistenceException("Error creating user '" + user.getUsername() + "' in database");
        } catch (SQLException e) {
            throw new PersistenceException("Error inserting User in database", e);
        }
    }

    @Override
    public Optional<User> update(User user) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getGender().name());
            preparedStatement.setString(5, user.getRole().name());
            preparedStatement.setString(6, user.getUrl());
            preparedStatement.setString(7, user.getShortDescription());
            preparedStatement.setString(8, user.getStatus().name());
            preparedStatement.setLong(9, user.getId());
            int numRecords = preparedStatement.executeUpdate();

            if (numRecords > 0) {
                return Optional.of(user);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new PersistenceException("Error executing update query in database", e);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> user = JdbcUtil.getEntities(resultSet, User.class);
            if (user.size() > 0) {
                return Optional.of(user.get(0));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new PersistenceException("Error executing database query", e);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
