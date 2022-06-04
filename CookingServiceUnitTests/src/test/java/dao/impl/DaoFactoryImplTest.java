package dao.impl;

import dao.AdminRepository;
import dao.CategoryRepository;
import dao.HomeCookRepository;
import dao.RecipeRepository;
import model.recipe.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DaoFactoryImplTest {



    DaoFactoryImpl daoFactory;

    @BeforeEach
    void setUp(){
        daoFactory = new DaoFactoryImpl();
    }


    @Test
    void testCreateRecipeRepository() {
        final RecipeRepository result = daoFactory.createRecipeRepository();

        assertThat(result)
                .isInstanceOf(RecipeRepositoryMemoryImpl.class);
    }

    @Test
    void testCreateAdminRepository() {
        final AdminRepository result = daoFactory.createAdminRepository();
        assertThat(result)
                .isInstanceOf(AdminRepositoryImpl.class);
    }

    @Test
    void testCreateHomeCookRepository() {
        final HomeCookRepository result = daoFactory.createHomeCookRepository();
        assertThat(result)
                .isInstanceOf(HomeCookRepositoryImpl.class);
    }

    @Test
    void testCreateCategoryRepository() {
        final CategoryRepository result = daoFactory.createCategoryRepository();
        assertThat(result).isInstanceOf(CategoryRepositoryImpl.class);
    }
}