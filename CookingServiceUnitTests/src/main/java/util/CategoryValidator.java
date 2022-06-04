package util;

import category.Category;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import model.users.HomeCook;

public class CategoryValidator implements GenericValidator<Category> {

    @Override
    public void validate(Category category) throws ConstraintViolationException, InvalidEntityDataException {
        if(category.getName().trim().length() < 2 || category.getName().trim().length() > 120){
            throw new InvalidEntityDataException(
                    "Category name length should be between 2 and 120 characters.");
        }

        if(category.getDescription().trim().length() < 10 || category.getDescription().trim().length() > 500){
            throw new InvalidEntityDataException(
                    "Category description  length should be between 10 and 500 characters.");
        }
    }
}
