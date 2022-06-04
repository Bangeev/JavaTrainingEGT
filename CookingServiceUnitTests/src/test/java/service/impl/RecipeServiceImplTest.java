package service.impl;

import dao.RecipeRepository;
import dao.impl.RecipeRepositoryMemoryImpl;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import util.RecipeValidator;

import java.security.PrivateKey;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static service.impl.AdminsServiceImplTest.SAMPLE_RECIPE;
import static service.impl.AdminsServiceImplTest.SAMPLE_RECIPE_COLLECTION;

class RecipeServiceImplTest {
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private RecipeValidator recipeValidator;

    private RecipeServiceImpl recipeService;


    @BeforeEach
    void setUp() {
        recipeRepository = Mockito.mock(RecipeRepository.class);
        recipeValidator = Mockito.mock(RecipeValidator.class);
        recipeService = new RecipeServiceImpl(recipeRepository,recipeValidator);
    }

    @Test
    @DisplayName("Trying to get all recipes from Collection")
    void testGetAllRecipes() {

        final Collection<Recipe> expectedResult = SAMPLE_RECIPE_COLLECTION;


        final Collection<Recipe> recipes = SAMPLE_RECIPE_COLLECTION;
        when(recipeRepository.findAll()).thenReturn(recipes);


        final Collection<Recipe> result = recipeService.getAllRecipes();


        assertThat(result).isEqualTo(expectedResult);


    }
    @Test
    @DisplayName("Trying to get all recipes from Collection but collection is null")
    void testGetAllRecipes_RecipeRepositoryReturnsNoItems() {

        when(recipeRepository.findAll()).thenReturn(Collections.emptyList());


        final Collection<Recipe> result = recipeService.getAllRecipes();


        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("Trying to get recipes ")
    void testGetRecipeById() throws NoneExistingEntityException {
        final Recipe expectedResult = SAMPLE_RECIPE;

        final Recipe recipe = SAMPLE_RECIPE;
                when(recipeRepository.findById(0L)).thenReturn(recipe);


        final Recipe result = recipeService.getRecipeById(0L);


        assertThat(result).isEqualTo(expectedResult);
    }
    @Test
    void testGetRecipeById_ThrowsNoneExistingEntityException(){

        final Recipe recipe = SAMPLE_RECIPE;
                when(recipeRepository.findById(1L)).thenReturn(recipe);


          assertThrows(NoneExistingEntityException.class,
                () -> recipeService.getRecipeById(0L));
//
//        assertEquals(exeption.getMessage(), "Recipe with ID - '" + recipeService.getRecipeById(0L) + "' does not exist.");
        // Run the test
//        assertThatThrownBy(() -> recipeService.getRecipeById(0L))
//                .isInstanceOf(NoneExistingEntityException.class);
//        assertThatExceptionOfType(NoneExistingEntityException.class)
//                .isThrownBy(() -> recipeService.getRecipeById(0L))
//        .withMessageStartingWith("Recipe with ID - '" + recipeService.getRecipeById(0L) + "' does not exist.");
//
    }

    @Test
    @DisplayName("Trying to add new recipes from Collection")
    void testAddRecipe() throws InvalidEntityDataException, ConstraintViolationException {

        final Recipe expectedResult = SAMPLE_RECIPE;

        final Recipe recipe = SAMPLE_RECIPE;

        final Recipe recipe1 = SAMPLE_RECIPE;
                when(recipeRepository.create(recipe)).thenReturn(recipe1);


        final Recipe result = recipeService.addRecipe(recipe);


        assertThat(result).isEqualTo(expectedResult);
        verify(recipeValidator).validate(expectedResult);
    }


    @Test
    void testAddRecipe_GenericValidatorThrowsInvalidEntityDataException() throws Exception {



        final Recipe recipe = SAMPLE_RECIPE;
        doThrow(InvalidEntityDataException.class).when(recipeValidator).validate(recipe);


        final Recipe recipe1 = SAMPLE_RECIPE;
        when(recipeRepository.create(recipe)).thenReturn(recipe1);




        assertThatThrownBy(() -> recipeService.addRecipe(recipe))
                .isInstanceOf(InvalidEntityDataException.class);
    }

    @Test
    @DisplayName("Trying to update recipes")
    void testUpdateRecipe() {

        final Recipe expectedResult = SAMPLE_RECIPE;

        final Recipe recipe = SAMPLE_RECIPE;

        final Recipe recipe1 = SAMPLE_RECIPE;
        when(recipeRepository.update(recipe)).thenReturn(recipe1);


        final Recipe result = recipeService.updateRecipe(recipe);


        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testDeleteRecipeById() throws NoneExistingEntityException {
        final Recipe expectedResult = SAMPLE_RECIPE;

        final Recipe recipe = SAMPLE_RECIPE;
        when(recipeRepository.delete(0L)).thenReturn(recipe);


        final Recipe result = recipeService.deleteRecipeById(0L);


        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteRecipeById_RecipeRepositoryThrowsNoneExistingEntityException() throws Exception {

        when(recipeRepository.delete(0L)).thenThrow(NoneExistingEntityException.class);


        assertThatThrownBy(() -> recipeService.deleteRecipeById(0L))
                .isInstanceOf(NoneExistingEntityException.class);
    }

    @Test
    void testFindByBrowsingCategories() {

        when(recipeRepository.findBySearchingInCategory()).thenReturn("result");


        final String result = recipeService.findByBrowsingCategories();


        assertThat(result).isEqualTo("result");
    }

    @Test
    void filterByCookingTime() {

        recipeService.filterByCookingTime();


        verify(recipeRepository).filterByCookingTime();
    }
}