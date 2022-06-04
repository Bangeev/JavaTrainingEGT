package service.impl;

import category.Category;
import dao.CategoryRepository;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import util.CategoryValidator;
import util.GenericValidator;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static service.impl.AdminsServiceImplTest.SAMPLE_CATEGORY;
import static service.impl.AdminsServiceImplTest.SAMPLE_CATEGORY_COLLECTION;

class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryValidator categoryValidator;

    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryValidator = Mockito.mock(CategoryValidator.class);
        categoryService = new CategoryServiceImpl(categoryRepository, categoryValidator);
    }

    @Test
    @DisplayName("Trying to fetch all categories in collection")
    void testgGetAllCategoriesFromCollection() {

        final Collection<Category> expectedResult = SAMPLE_CATEGORY_COLLECTION;

        final Collection<Category> categories = SAMPLE_CATEGORY_COLLECTION;
        when(categoryRepository.findAll()).thenReturn(categories);


        final Collection<Category> result = categoryService.getAllCategories();


        assertThat(result).isEqualTo(expectedResult);
    }
    @Test
    @DisplayName("Trying to fetch all categories in collection but there are null")
    void testGetAllCategories_CategoryRepositoryReturnsNoItems() {

        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());


        final Collection<Category> result = categoryService.getAllCategories();


        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("Trying to add new category")
    void testAddCategory() throws InvalidEntityDataException, ConstraintViolationException {

        final Category category = SAMPLE_CATEGORY;
        final Category expectedResult = SAMPLE_CATEGORY;

        final Category category1 = SAMPLE_CATEGORY;
        when(categoryRepository.create(SAMPLE_CATEGORY)).thenReturn(category1);


        final Category result = categoryService.addCategory(category);


        assertThat(result).isEqualTo(expectedResult);
        verify(categoryValidator).validate(SAMPLE_CATEGORY);
    }

    @Test
    @DisplayName("Trying to fetch exception from addCategory method")
    void testAddCategory_GenericValidatorThrowsInvalidEntityDataException() throws Exception {

        final Category category = SAMPLE_CATEGORY;
        doThrow(InvalidEntityDataException.class).when(categoryValidator).validate(SAMPLE_CATEGORY);


        final Category category1 = SAMPLE_CATEGORY;
        when(categoryRepository.create(SAMPLE_CATEGORY)).thenReturn(category1);


        assertThatThrownBy(() -> categoryService.addCategory(category))
                .isInstanceOf(InvalidEntityDataException.class);
    }

    @Test
    @DisplayName("Trying to update existing category")
    void testUpdateCategory() throws NoneExistingEntityException {
        final Category category = SAMPLE_CATEGORY;
        final Category expectedResult = SAMPLE_CATEGORY;

        final Category category1 = SAMPLE_CATEGORY;
        when(categoryRepository.update(SAMPLE_CATEGORY)).thenReturn(category1);


        final Category result = categoryService.updateCategory(category);


        assertThat(result).isEqualTo(expectedResult);
    }
    @Test
    @DisplayName("Trying to fetch exception from updatingCategory method")
    void testUpdateCategory_ThrowsNoneExistingEntityException() {

        final Category category = SAMPLE_CATEGORY;


        final Category category1 = SAMPLE_CATEGORY;
        when(categoryRepository.update(SAMPLE_CATEGORY)).thenReturn(category1);



        assertDoesNotThrow(() -> categoryService.updateCategory(category));
//        assertThatThrownBy(() -> categoryService.updateCategory(category))
//                .isInstanceOf(NoneExistingEntityException.class);
//        assertThatExceptionOfType(NoneExistingEntityException.class)
//                .isThrownBy(() -> categoryService.updateCategory(category))
//                .withCauseExactlyInstanceOf(NoneExistingEntityException.class)
//                .withMessageContaining("test");
    }

    @Test
    @DisplayName("Trying to delete category by its id ")
    void testDeleteCategoryById() throws NoneExistingEntityException {
        // Setup
        final Category expectedResult = SAMPLE_CATEGORY;


        final Category category = SAMPLE_CATEGORY;
        when(categoryRepository.delete(0L)).thenReturn(category);


        final Category result = categoryService.deleteCategoryById(0L);


        assertThat(result).isEqualTo(expectedResult);
    }
    @Test
    @DisplayName("Trying to fetch exception when trying to delete by id category")
    void testDeleteCategoryById_CategoryRepositoryThrowsNoneExistingEntityException() throws Exception {

        when(categoryRepository.delete(0L)).thenThrow(NoneExistingEntityException.class);


        assertThatThrownBy(() -> categoryService.deleteCategoryById(0L))
                .isInstanceOf(NoneExistingEntityException.class);
    }
}