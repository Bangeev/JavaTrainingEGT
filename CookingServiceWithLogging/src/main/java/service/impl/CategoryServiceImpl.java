package service.impl;

import category.Category;
import dao.CategoryRepository;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import service.CategoryService;
import util.GenericValidator;

import java.time.LocalDate;
import java.util.Collection;

public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private GenericValidator<Category> genericValidator;


    public CategoryServiceImpl(CategoryRepository categoryRepository, GenericValidator<Category> genericValidator) {
        this.categoryRepository = categoryRepository;
        this.genericValidator = genericValidator;
    }

    @Override
    public Collection<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) throws InvalidEntityDataException {

        try {
            genericValidator.validate(category);
        } catch (ConstraintViolationException e) {
            throw new InvalidEntityDataException(
                    String.format("Invalid category data for category '%s'.", category.getName()),
                    e
            );
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
