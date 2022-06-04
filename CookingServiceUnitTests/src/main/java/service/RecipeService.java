package service;

import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;

import java.util.Collection;

public interface RecipeService {
    Collection<Recipe> getAllRecipes();
    Recipe getRecipeById(Long id) throws NoneExistingEntityException;
    Recipe addRecipe(Recipe recipe) throws InvalidEntityDataException;
    Recipe updateRecipe(Recipe recipe) throws NoneExistingEntityException;
    Recipe deleteRecipeById(Long id) throws NoneExistingEntityException;
    String findByBrowsingCategories();
    void filterByCookingTime();
}
