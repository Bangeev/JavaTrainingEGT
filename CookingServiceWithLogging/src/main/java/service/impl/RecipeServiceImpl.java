package service.impl;

import dao.RecipeRepository;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;
import service.RecipeService;
import util.GenericValidator;

import java.time.LocalDate;
import java.util.Collection;

public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    GenericValidator<Recipe> genericValidator;

    public RecipeServiceImpl(RecipeRepository recipeRepository, GenericValidator<Recipe> genericValidator) {
        this.recipeRepository = recipeRepository;
        this.genericValidator = genericValidator;
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

        try {
            genericValidator.validate(recipe);
        } catch (ConstraintViolationException e) {
            throw new InvalidEntityDataException(
                    String.format("Invalid Recipe data for Recipe '%s'.", recipe.getTitle()),
                    e
            );
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
