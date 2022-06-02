package service.impl;

import dao.RecipeRepository;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;
import service.RecipeService;

import java.time.LocalDate;
import java.util.Collection;

public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }



    @Override
    public Collection<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) throws NoneExistingEntityException {
        var recipe = recipeRepository.findById(id);
        if (recipe == null) {
            throw new NoneExistingEntityException("Recipe with ID - '" + id + "' does not exist.");
        }
        return recipe;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) throws InvalidEntityDataException {
        if (recipe.getTitle().trim().length() < 2 || recipe.getTitle().trim().length() > 120) {
            throw new InvalidEntityDataException(
                    "Recipe title length should be between 2 and 120 characters.");
        }

        if (recipe.getShortDescription().trim().length() < 2 || recipe.getShortDescription().trim().length() > 250) {
            throw new InvalidEntityDataException(
                    "Recipe short description  length should be between 2 and 250 characters.");
        }

        if (recipe.getCookingTime() <= 0) {
            throw new InvalidEntityDataException("Recipe cooking time can not be negative.");
        }

        if (recipe.getUsedProducts().trim().length() < 20 || recipe.getUsedProducts().trim().length() > 500) {
            throw new InvalidEntityDataException(
                    "Recipe used products  length should be between 20 and 500 characters.");
        }

        if (recipe.getDescription().trim().length() < 150 || recipe.getDescription().trim().length() > 2500) {
            throw new InvalidEntityDataException(
                    "Recipe description length should be between 150 and 2500 characters.");
        }


        return recipeRepository.create(recipe);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) throws NoneExistingEntityException {
        recipe.setModified(LocalDate.now());
        return recipeRepository.update(recipe);
    }

    @Override
    public Recipe deleteRecipeById(Long id) throws NoneExistingEntityException {
        return recipeRepository.delete(id);
    }

    @Override
    public String findByBrowsingCategories() {
        return recipeRepository.findBySearchingInCategory();
    }

    @Override
    public void filterByCookingTime() {
        recipeRepository.filterByCookingTime();
    }
}
