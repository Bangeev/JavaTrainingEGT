package homework.dao;

import homework.exception.PersistenceException;
import homework.model.Recipe;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.sql.*;
import java.util.Collection;
import java.util.Optional;

public interface RecipeRepositoryJdbi {
    public static final String RECIPES_TABLE = "recipe";
//    @SqlUpdate("CREATE TABLE `" + RECIPES_TABLE + "` ( " +
//            " `id`                 bigint        NOT NULL AUTO_INCREMENT, " +
//            "  `userId`             bigint        NOT NULL, " +
//            "  `name`               varchar(80)   NOT NULL, " +
//            " `shortDescription`   varchar(256)  NOT NULL, " +
//            "    `cookingTimeMinutes` int           NOT NULL, " +
//            "  `products`           varchar(255)  NOT NULL, " +
//            "  `image`              varchar(255)  NOT NULL, " +
//            "  `longDescription`    varchar(2048) NOT NULL, " +
//            "  `tags`               varchar(255) DEFAULT NULL, " +
//            " `created`            DATETIME NULL DEFAULT NOW(), " +
//            " `modified`           DATETIME NULL DEFAULT NOW(), " +
//            "     PRIMARY KEY (`id`)" +
//            ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;")
//    public boolean createTable();

    @SqlUpdate("INSERT INTO `" + RECIPES_TABLE + "` " +
            " (`userId`, `name`, `shortDescription`, `cookingTimeMinutes`,`products` , `image`, `longDescription`, `tags`, `created`, `modified`) " +
            " VALUES (:userId, :name, :shortDescription, :cookingTimeMinutes, :products, :image, :longDescription, :tags, :created, :modified) ")
    @GetGeneratedKeys("id")
    long create(@BindBean Recipe recipe);

    @SqlBatch("INSERT INTO `" + RECIPES_TABLE + "` " +
            " (`userId`, `name`, `shortDescription`, `cookingTimeMinutes`,`products` , `image`, `longDescription`, `tags`, `created`, `modified`) " +
            "VALUES (:userId, :name, :shortDescription, :cookingTimeMinutes, :products, :image, :longDescription, :tags, :created, :modified) ")
    @GetGeneratedKeys("id")
    long[] createBatch(@BindBean Iterable<Recipe> recipes);


    @SqlUpdate("UPDATE `" + RECIPES_TABLE + "` " +
            " SET `userId` = :userId, `name` = :name, `shortDescription` = :shortDescription, `cookingTimeMinutes` = :cookingTimeMinutes, `products` = :products, `image` = :image, `longDescription` = :longDescription, `tags` = :tags" +
            " WHERE id = :id;")
    boolean update(@BindBean Recipe entity);

    @SqlQuery("SELECT * FROM `" + RECIPES_TABLE + "`;")
    @RegisterBeanMapper(Recipe.class)
    Collection<Recipe> findAll();

    @SqlQuery("SELECT * FROM `" + RECIPES_TABLE + "` WHERE `id` = :id;")
    @RegisterBeanMapper(Recipe.class)
    Optional<Recipe> findById(@Bind("id") Long id);

    @SqlUpdate("DELETE FROM `" + RECIPES_TABLE + "` WHERE id = :id; ")
    boolean deleteById(Long id);

    @SqlQuery("SELECT * FROM `" + RECIPES_TABLE + "` WHERE INSTR(tags, :tags) > 0 ;")
    @RegisterBeanMapper(Recipe.class)
    Collection<Recipe> findAllByTagsIn(String tags);


    @SqlQuery("SELECT * FROM `" + RECIPES_TABLE + "` WHERE INSTR(name, :name) > 0 ;;")
    @RegisterBeanMapper(Recipe.class)
    Collection<Recipe> findAllByNameContains(String name);
}
