package homework.dao.impl;


import homework.exception.PersistenceException;
import homework.model.Recipe;
import homework.model.User;
import homework.util.JdbcUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbsRepositoryRecipeImpl {
    private static final String FIND_ALL_RECIPES = "select * from recipe; ";
    private static final String FIND_RECIPE_BY_ID = "select * from recipe where id = ?; ";
    private static final String DELETE_RECIPE_BY_ID = "delete from recipe where id = ?; ";


    private final Connection connection;

    protected AbsRepositoryRecipeImpl(Connection connection) {
        this.connection = connection;
    }

    protected Connection getConnection() {
        return this.connection;
    }

    public Optional<Recipe> findById(Long id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_RECIPE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Recipe> entity = JdbcUtil.getEntities(resultSet, Recipe.class);
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

    public List<Recipe> findAll() {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_ALL_RECIPES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Recipe> entities = JdbcUtil.getEntities(resultSet, Recipe.class);
            return entities;

        } catch (SQLException e) {
            throw new PersistenceException("Error executing database query", e);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Recipe> deleteById(Long id) {
        Optional<Recipe> entity = findById(id);

        if (entity.isPresent()) {
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_RECIPE_BY_ID)) {
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
