package homework.dao.impl;


import homework.exception.PersistenceException;
import homework.model.User;
import homework.util.JdbcUtil;


import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbsRepositoryUserImpl {
    private static final String FIND_ALL_USERS = "select * from user; ";
    private static final String FIND_USER_BY_ID = "select * from user where id = ?; ";
    private static final String DELETE_USER_BY_ID = "delete from user where id = ?; ";
 

    private final Connection connection;

    protected AbsRepositoryUserImpl(Connection connection) {
        this.connection = connection;
    }

    protected Connection getConnection() {
        return this.connection;
    }

    public Optional<User> findById(Long id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> entity = JdbcUtil.getEntities(resultSet, User.class);
            if (entity.size() > 0) {
                return Optional.of(entity.get(0));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new PersistenceException("Error executing database query", e);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findAll() {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> entities = JdbcUtil.getEntities(resultSet, User.class);
            return entities;

        } catch (SQLException e) {
            throw new PersistenceException("Error executing database query", e);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> deleteById(Long id) {
        Optional<User> entity = findById(id);

        if (entity.isPresent()) {
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_USER_BY_ID)) {
                preparedStatement.setLong(1, id);
                int numberOfRow = preparedStatement.executeUpdate();

                if (numberOfRow > 0) {
                    return entity;
                }

            } catch (SQLException e) {
                throw new PersistenceException("Error executing delete query in database", e);
            }
        }
        return entity;
    }





}
