package homework.dao.impl;


import homework.dao.RecipeRepositoryJdbc;
import homework.exception.PersistenceException;
import homework.model.Recipe;
import homework.util.JdbcUtil;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class RecipeRepositoryJdbcImpl extends AbsRepositoryRecipeImpl implements RecipeRepositoryJdbc {
    private static final String FIND_ALL_BY_NAME = "select * from recipe where INSTR(name, ?) > 0 ; ";
    private static final String FIND_ALL_BY_TAGS = "select * from recipe where INSTR(tags, ?) > 0 ; ";
    private static final String UPDATE_RECIPE = "update recipe " +
            "set `userId`=?, `name`=?, `shortDescription`=?, `cookingTimeMinutes`=?," +
            " `products`=?, `image`=?, `longDescription`=?, `tags`=? " +
            "where id = ?; ";

    private static final String INSERT_NEW_RECIPE = "INSERT INTO recipe " +
            "(`userId`, `name`, `shortDescription`, `cookingTimeMinutes`,`products` , `image`, `longDescription`, `tags`, `created`, `modified`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";



    public RecipeRepositoryJdbcImpl(Connection connection) {
        super(connection);
    }

    protected Connection getConnection() {
        return super.getConnection();
    }



    @Override
    public Recipe create(Recipe recipe) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_NEW_RECIPE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, recipe.getUserId());
            preparedStatement.setString(2, recipe.getName());
            preparedStatement.setString(3, recipe.getShortDescription());
            preparedStatement.setInt(4, recipe.getCookingTimeMinutes());
            preparedStatement.setString(5, recipe.getProducts());
            preparedStatement.setString(6, recipe.getImage());
            preparedStatement.setString(7, recipe.getLongDescription());
            preparedStatement.setString(8,  recipe.getTags());
            preparedStatement.setTimestamp(9, Timestamp.valueOf(recipe.getCreated()));
            preparedStatement.setTimestamp(10, Timestamp.valueOf(recipe.getModified()));

            int numRecords = preparedStatement.executeUpdate();

            if (numRecords > 0) {
                ResultSet keys = preparedStatement.getGeneratedKeys();
                try {
                    keys.next();
                    recipe.setId(keys.getLong(1));
                    return recipe;
                } catch (SQLException e) {
                    throw new PersistenceException("Error fetching generated key for Recipe: " + recipe.getName(), e);
                }
            }
            throw new PersistenceException("Error creating recipe '" + recipe.getName() + "' in database");
        } catch (SQLException e) {
            throw new PersistenceException("Error inserting Recipe in database", e);
        }
    }

    @Override
    public Optional<Recipe> update(Recipe recipe) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_RECIPE)) {
            preparedStatement.setLong(1, recipe.getUserId());
            preparedStatement.setString(2, recipe.getName());
            preparedStatement.setString(3, recipe.getShortDescription());
            preparedStatement.setInt(4, recipe.getCookingTimeMinutes());
            preparedStatement.setString(5, recipe.getProducts());
            preparedStatement.setString(6, recipe.getImage());
            preparedStatement.setString(7, recipe.getLongDescription());
            preparedStatement.setString(8, recipe.getTags());
            preparedStatement.setLong(9, recipe.getId());

            int numRecords = preparedStatement.executeUpdate();

            if (numRecords > 0) {
                return Optional.of(recipe);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new PersistenceException("Error executing update query in database", e);
        }
    }


    @Override
    public List<Recipe> findAllByTagsIn(String tags) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_ALL_BY_TAGS)) {
            preparedStatement.setString(1, tags);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Recipe> entities = JdbcUtil.getEntities(resultSet, Recipe.class);
            return entities;

        } catch (SQLException e) {
            throw new PersistenceException("Error executing database query", e);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Recipe> findAllByNameContains(String name) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_ALL_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Recipe> entities = JdbcUtil.getEntities(resultSet, Recipe.class);
            return entities;

        } catch (SQLException e) {
            throw new PersistenceException("Error executing database query", e);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
