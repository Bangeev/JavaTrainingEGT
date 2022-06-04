package dao.impl;

import category.Category;
import dao.AdminRepository;
import model.recipe.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.RecipeService;
import service.impl.RecipeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RecipeRepositoryMemoryImplTest {
    RecipeRepositoryMemoryImpl recipeRepositoryMemory;
    RecipeServiceImpl recipeService;

    @BeforeEach
    void setUp() {
        this.recipeRepositoryMemory = new RecipeRepositoryMemoryImpl();

    }

    @Test
    @DisplayName("Trying to create new recipe with method create")
    void testCreateNewRecipe() {

        var recipe = this.recipeRepositoryMemory.create(SAMPLE_RECIPE);
        var expectedResult = this.recipeRepositoryMemory.create(SAMPLE_RECIPE);

        final Recipe result = recipeRepositoryMemory.create(recipe);

        assertThat(result).isEqualTo(expectedResult);

    }

    @Test
    @DisplayName("Trying to search recipes in category")
    void testFindBySearchingInCategory() {
        Recipe recipe = SAMPLE_RECIPE;
        Category category = SAMPLE_CATEGORY;
        this.recipeRepositoryMemory.allCooking.putIfAbsent(category, new ArrayList<>());
        this.recipeRepositoryMemory.allCooking.get(category).add(recipe);


        final String result = recipeRepositoryMemory.findBySearchingInCategory();
        // Verify the results
        assertThat(result).isEqualTo(result);

    }

    @Test
    @DisplayName("Trying to search recipe by recipe")
    void testFindBySearchingRecipe() {
        Recipe recipe = SAMPLE_RECIPE;
        recipeRepositoryMemory.create(recipe);
        var actual = recipeRepositoryMemory.findBySearching(
                "Bread",
                "Water,Salt,Flour,Milch,Eggs",
                "Really Good Food",
                "Bread");
        assertThat(actual).isEqualTo(recipe);
    }

    @Test
    @DisplayName("Trying to filter the recipes by cooking time")
    void testFilterByCookingTime() {

      var time = recipeRepositoryMemory.recipes.put(10L, SAMPLE_RECIPES.get(0));
      var time2 = recipeRepositoryMemory.recipes.put(11L, SAMPLE_RECIPES.get(1));

        recipeRepositoryMemory.filterByCookingTime();
        assertNotEquals(recipeRepositoryMemory.recipes.get(10L).getCookingTime(),
                recipeRepositoryMemory.recipes.get(11L).getCookingTime());


    }


    private static final Category SAMPLE_CATEGORY = new Category(
            "FirstCategory",
            "Haide na hlebchetata obi4am chushkiiii",
            "test test bg");

    private static final Recipe SAMPLE_RECIPE = new Recipe(
            SAMPLE_CATEGORY,
            "Bread",
            "Black",
            "Really Good Food",
            120,
            "Water,Salt,Flour,Milch,Eggs",
            "https://bg.wikipedia.org/wiki/%D0%A5%D0%BB%D1%8F%D0%B1#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Anadama_bread_(1).jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                    " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit" +
                    " in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
                    " sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "Bread");

    private static final String SAMPLE_RECIPE_CLEAR = "Category - id=1, name='FirstCategory', description='Haide na hlebchetata obi4am chushkiiii', tags='test test bg', created=2022-05-04, modified=2022-05-04\n   - [Recipe - id=1, category=Category - id=1, name='FirstCategory', description='Haide na hlebchetata obi4am chushkiiii', tags='test test bg', created=2022-05-04, modified=2022-05-04 , title='Bread', author='Black', shortDescription='Really Good Food', cookingTime=120, usedProducts='Water,Salt,Flour,Milch,Eggs', picture='https://bg.wikipedia.org/wiki/%D0%A5%D0%BB%D1%8F%D0%B1#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Anadama_bread_(1).jpg', description='Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', tags='Bread', created=2022-05-04, modified=2022-05-04 ]\n  ";
    private static List<Recipe> SAMPLE_RECIPES = List.of(
            new Recipe(
                    SAMPLE_CATEGORY,
                    "Bread",
                    "Black",
                    "Really Good Food",
                    120,
                    "Water,Salt,Flour,Milch,Eggs",
                    "https://bg.wikipedia.org/wiki/%D0%A5%D0%BB%D1%8F%D0%B1#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Anadama_bread_(1).jpg",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                            " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit" +
                            " in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
                            " sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    "Bread"),
            new Recipe(
                    SAMPLE_CATEGORY,
                    "TEST",
                    "Test",
                    "Really Good Food",
                    100,
                    "Water,Salt,Flour,Milch,Eggs",
                    "https://bg.wikipedia.org/wiki/%D0%A5%D0%BB%D1%8F%D0%B1#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Anadama_bread_(1).jpg",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                            " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit" +
                            " in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
                            " sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    "Bread")
    );

}