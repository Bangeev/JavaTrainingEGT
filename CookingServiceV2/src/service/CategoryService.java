package service;

import category.Category;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;

import java.util.Collection;

public interface CategoryService {
    Collection<Category> getAllCategories();
    Category addCategory(Category category) throws InvalidEntityDataException;
    Category updateCategory(Category category) throws NoneExistingEntityException;
    Category deleteCategoryById(Long id) throws NoneExistingEntityException;

}
