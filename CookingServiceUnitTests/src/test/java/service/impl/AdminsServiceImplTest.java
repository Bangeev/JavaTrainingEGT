package service.impl;

import category.Category;
import common.Gender;
import common.Role;
import common.Status;
import dao.AdminRepository;
import dao.impl.AdminRepositoryImpl;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;
import model.users.Admins;
import model.users.HomeCook;
import model.users.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import util.AdminValidator;
import util.GenericValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdminsServiceImplTest {
    @Mock
    private AdminRepository mockAdminRepository;
    @Mock
    AdminValidator adminValidator;

    private AdminsServiceImpl adminsServiceImplUnderTest;

    @BeforeEach
    void setup() {
        this.adminValidator = Mockito.mock(AdminValidator.class);
        this.mockAdminRepository = Mockito.mock(AdminRepository.class);
        this.adminsServiceImplUnderTest = new AdminsServiceImpl(
                mockAdminRepository, adminValidator
        );
        SAMPLE_USERS.forEach(admins -> this.mockAdminRepository.create(admins));
    }

    @Test
    @DisplayName("Trying to fetch all users that are admins")
    void testGetAllAdmins() {

        final Collection<Admins> admins = ADMINS_COLLECTION;
        when(mockAdminRepository.findAll()).thenReturn(admins);


        final Collection<Admins> result = adminsServiceImplUnderTest.getAllAdmins();


        assertThat(result).isEqualTo(admins);
    }

    @Test
    @DisplayName("Trying to fetch all users that are admins but its NULL")
    void testGetAllAdmins_AdminRepositoryReturnsNoItems() {

        when(mockAdminRepository.findAll()).thenReturn(Collections.emptyList());


        final Collection<Admins> result = adminsServiceImplUnderTest.getAllAdmins();


        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("Trying to add admins")
    void testAddAdmin() throws InvalidEntityDataException, ConstraintViolationException {

        final Admins admin = SAMPLE_USER;
        final Admins expectedResult = SAMPLE_USER;


        when(mockAdminRepository.create(SAMPLE_USER)).thenReturn(SAMPLE_USER);

        final Admins result = adminsServiceImplUnderTest.addAdmin(admin);


        assertThat(result).isEqualTo(expectedResult);
        verify(adminValidator).validate(SAMPLE_USER);
    }

    @Test
    @DisplayName("Trying to fetch addadmi() exception")
    void testAddAdmin_GenericValidatorThrowsInvalidEntityDataException() throws Exception {

        final Admins admin = SAMPLE_USER;
        doThrow(InvalidEntityDataException.class).when(adminValidator).validate(SAMPLE_USER);

        final Admins admins = SAMPLE_USER;
        when(mockAdminRepository.create(SAMPLE_USER)).thenReturn(admins);

        assertThatThrownBy(() -> adminsServiceImplUnderTest.addAdmin(admin))
                .isInstanceOf(InvalidEntityDataException.class);
//        assertThatExceptionOfType(InvalidEntityDataException.class)
//                .isThrownBy(() ->adminsServiceImplUnderTest.addAdmin(admin))
//                .withCauseExactlyInstanceOf(ConstraintViolationException.class);
    }


    @Test
    @DisplayName("Trying to update user")
    void testUpdateAdmin() throws NoneExistingEntityException {

        final Admins expectedResult = SAMPLE_USER;
        final Admins admins = SAMPLE_USER;
        when(mockAdminRepository.update(admins)).thenReturn(admins);

        final Admins result = adminsServiceImplUnderTest.updateAdmin(admins);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Trying to fetch exception from updateAdmin()")
    void testUpdateAdmin_ThrowsNoneExistingEntityException() throws InvalidEntityDataException {

        final Admins admin = SAMPLE_USER;
        doThrow(InvalidEntityDataException.class)
                .when(adminValidator).validate(SAMPLE_USER);

        final Admins admins = SAMPLE_USER;
        when(mockAdminRepository.update(SAMPLE_USER)).thenReturn(admins);

        assertThatThrownBy(() -> adminsServiceImplUnderTest.addAdmin(admin))
                .isInstanceOf(InvalidEntityDataException.class);
    }

    @Test
    @DisplayName("Trying to delete user by id")
    void testDeleteAdminById() throws NoneExistingEntityException {

        final Admins expectedResult = SAMPLE_USER;
        final Admins admins = SAMPLE_USER;
        when(mockAdminRepository.delete(0L)).thenReturn(admins);

        final Admins result = adminsServiceImplUnderTest.deleteAdminById(0L);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Trying to fetch exception from deleteAdminByID")
    void testDeleteAdminById_AdminRepositoryThrowsNoneExistingEntityException() throws Exception {

        when(mockAdminRepository.delete(0L)).thenThrow(NoneExistingEntityException.class);


        assertThatThrownBy(() -> adminsServiceImplUnderTest.deleteAdminById(0L))
                .isInstanceOf(NoneExistingEntityException.class);
    }

    @Test
    @DisplayName("Trying to add new category")
    void testAddNewCategory() {

        final Category category = SAMPLE_CATEGORY;

        adminsServiceImplUnderTest.addNewCategory(category);


        verify(mockAdminRepository).addNewCategories(category);

    }

    @Test
    void addNewRecipe() {

        final Recipe recipe = SAMPLE_RECIPE;

        adminsServiceImplUnderTest.addNewRecipe(recipe);


        verify(mockAdminRepository).addNewRecipes(recipe);
    }

    @Test
    void changeUserData() {
        final Users user = SAMPLE_USER;

        adminsServiceImplUnderTest.changeUserData(user, "username", "value");


        verify(mockAdminRepository).changeUserData(user, "username", "value");
    }


    public static final Collection<Category> SAMPLE_CATEGORY_COLLECTION = List.of(new Category(
            "FirstCategory",
            "Haide na hlebchetata obi4am chushkiiii",
            "test test bg")
    );
    public static final Category SAMPLE_CATEGORY = new Category(
            "FirstCategory",
            "Haide na hlebchetata obi4am chushkiiii",
            "test test bg");

    public static final Recipe SAMPLE_RECIPE = new Recipe(
            SAMPLE_CATEGORY,
            "Bread",
            "Black",
            "Really Good Food",
            120,
            "Water,Salt,Flour,Milch,Eggs",
            "https://bg.wikipedia.org/wiki/%D0%A5%D0%BB%D1%8F%D0%B1#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Anadama_bread_(1).jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                    " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit" +
                    " in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
                    " sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "Bread");

    public static final Collection<Recipe> SAMPLE_RECIPE_COLLECTION = List.of(new Recipe(
            SAMPLE_CATEGORY,
            "Bread",
            "Black",
            "Really Good Food",
            120,
            "Water,Salt,Flour,Milch,Eggs",
            "https://bg.wikipedia.org/wiki/%D0%A5%D0%BB%D1%8F%D0%B1#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Anadama_bread_(1).jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                    " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit" +
                    " in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
                    " sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "Bread"));


    public static final Collection<Admins> ADMINS_COLLECTION = List.of(
            new Admins("Georgi", "Bangeev", "bangeev13@gmail.com",
                    "bangeev", "bangeev", Gender.MALE.name(),
                    Status.ACTIVE.name(), new ArrayList<>(), new ArrayList<>()),
            new Admins("Pesho", "Peshov", "peshov13@gmail.com",
                    "pesho", "pesho", Gender.MALE.name(),
                    Status.ACTIVE.name(), new ArrayList<>(), new ArrayList<>()),
            new Admins("Geri", "Gerova", "geri13@gmail.com",
                    "geri", "geri", Gender.FEMALE.name(),
                    Status.ACTIVE.name(), new ArrayList<>(), new ArrayList<>())
    );

    private static final Admins SAMPLE_USER = new Admins("Georgi",
            "Bangeev",
            "bangeev13@gmail.com",
            "bangeev",
            "bangeevv",
            Gender.MALE.name(),
            Status.ACTIVE.name(),
            new ArrayList<>(),
            new ArrayList<>());

    private static final List<Admins> SAMPLE_USERS = List.of(
            new Admins("Georgi", "Bangeev", "bangeev13@gmail.com",
                    "bangeev", "bangeev", Gender.MALE.name(),
                    Status.ACTIVE.name(), new ArrayList<>(), new ArrayList<>()),
            new Admins("Pesho", "Peshov", "peshov13@gmail.com",
                    "pesho", "pesho", Gender.MALE.name(),
                    Status.ACTIVE.name(), new ArrayList<>(), new ArrayList<>()),
            new Admins("Geri", "Gerova", "geri13@gmail.com",
                    "geri", "geri", Gender.FEMALE.name(),
                    Status.ACTIVE.name(), new ArrayList<>(), new ArrayList<>())
    );


}