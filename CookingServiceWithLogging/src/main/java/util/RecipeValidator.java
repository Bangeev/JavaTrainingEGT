package util;

import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import model.recipe.Recipe;

public class RecipeValidator implements GenericValidator<Recipe> {
    @Override
    public void validate(Recipe recipe) throws ConstraintViolationException, InvalidEntityDataException {
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

    }
}
