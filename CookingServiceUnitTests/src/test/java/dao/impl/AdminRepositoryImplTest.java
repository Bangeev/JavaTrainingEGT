package dao.impl;

import category.Category;
import common.Gender;
import common.Role;
import common.Status;
import model.recipe.Recipe;
import model.users.Admins;
import model.users.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AdminRepositoryImplTest {

    private AdminRepositoryImpl adminRepository;


    @BeforeEach
    void setUp() {

        this.adminRepository = new AdminRepositoryImpl();
        SAMPLE_USERS.forEach(admins -> adminRepository.create(admins));


    }

    @Test
    @DisplayName("Change user data with the method from AdminRepository")
    void testChangeUserData() {

        Admins admin = SAMPLE_USER;

        Admins actual = this.adminRepository.create(
                new Admins(admin.getFirstName(),
                        admin.getLastName(),
                        admin.getEmail(),
                        admin.getUsername(),
                        admin.getPassword(),
                        admin.getGender(),
                        admin.getStatus(),
                        admin.getRecipes(),
                        admin.getCategoriesModerate()
                )
        );

        adminRepository.changeUserData(actual, "username", "value");

        assertNotEquals(admin.getUsername(), actual.getUsername());




    }

    @Test
    @DisplayName("Add new categories with the method from AbstractRepository")
    void testAddingNewCategories() {
        Category category = SAMPLE_CATEGORY;

        adminRepository.addNewCategories(category);


        assertTrue(this.adminRepository.allCooking.containsKey(category));
    }

    @Test
    @DisplayName("Add new recipes with the method from AbstractRepository")
    void testAddingNewRecipes() {
        Recipe recipe = SAMPLE_RECIPE;
        Category category = SAMPLE_CATEGORY;

        adminRepository.addNewCategories(category);
        adminRepository.addNewRecipes(recipe);
        var actual = false;
        for (List<Recipe> value : this.adminRepository.allCooking.values()) {
            if (value.contains(recipe)) {
                actual = true;
                assertThat(recipe).isInstanceOf(Recipe.class);
            }
        }

        assertTrue(actual);


    }

    private static final Long SAMPLE_ID = 1L;


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

    private static final Admins SAMPLE_USER = new Admins("Georgi",
            "Bangeev",
            "bangeev13@gmail.com",
            "bangeev",
            "bangeev",
            Gender.MALE.name(),
            Status.ACTIVE.name(),
            new ArrayList<>(),
            new ArrayList<>());

    private static final Category SAMPLE_CATEGORY = new Category(
            "FirstCategory",
            "Haide na hlebchetata obi4am chushkiiii",
            "test test bg");

    private static final Recipe SAMPLE_RECIPE = new Recipe(SAMPLE_CATEGORY, "Bread", "Black", "Really Good Food",
            120, "Water,Salt,Flour,Milch,Eggs",
            "https://bg.wikipedia.org/wiki/%D0%A5%D0%BB%D1%8F%D0%B1#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Anadama_bread_(1).jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                    " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit" +
                    " in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
                    " sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "Bread");

}


