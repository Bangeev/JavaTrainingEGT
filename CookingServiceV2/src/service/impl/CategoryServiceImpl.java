package service.impl;

import category.Category;
import dao.CategoryRepository;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import service.CategoryService;

import java.time.LocalDate;
import java.util.Collection;

public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Collection<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) throws InvalidEntityDataException {
        if(category.getName().trim().length() < 2 || category.getName().trim().length() > 120){
            throw new InvalidEntityDataException(
                    "Category name length should be between 2 and 120 characters.");
        }

        if(category.getDescription().trim().length() < 10 || category.getDescription().trim().length() > 500){
            throw new InvalidEntityDataException(
                    "Category description  length should be between 10 and 500 characters.");
        }

        return categoryRepository.create(category);
    }

    @Override
    public Category updateCategory(Category category) throws NoneExistingEntityException {
        category.setModified(LocalDate.now());
        return categoryRepository.update(category);
    }

    @Override
    public Category deleteCategoryById(Long id) throws NoneExistingEntityException {
        return categoryRepository.delete(id);
    }
}
