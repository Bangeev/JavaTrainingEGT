package dao.impl;

import category.Category;
import common.Gender;
import common.Status;
import exception.InvalidEntityDataException;
import model.recipe.Recipe;
import model.users.HomeCook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HomeCookRepositoryImplTest {

    private HomeCookRepositoryImpl homeCookRepository;


    @BeforeEach
    void setUp() {
        this.homeCookRepository = new HomeCookRepositoryImpl();
    }

    @Test
    @DisplayName("Trying to update the first name of user")
    void testUpdateFirstName() {
        HomeCook homeCook = SAMPLE_HOMECOOK;
        var actual = new HomeCook(
                homeCook.getFirstName(),
                homeCook.getLastName(),
                homeCook.getEmail(),
                homeCook.getUsername(),
                homeCook.getPassword(),
                homeCook.getGender(),
                homeCook.getStatus(),
                homeCook.getRecipes()
        );
        this.homeCookRepository.updateFirstName(homeCook, "CHANGED");
        assertThat(actual.getFirstName()).isNotEqualTo(homeCook.getFirstName());


    }

    @Test
    @DisplayName("Trying to update the last name of user")
    void testUpdateLastName() {
        HomeCook homeCook = SAMPLE_HOMECOOK;
        var actual = new HomeCook(
                homeCook.getFirstName(),
                homeCook.getLastName(),
                homeCook.getEmail(),
                homeCook.getUsername(),
                homeCook.getPassword(),
                homeCook.getGender(),
                homeCook.getStatus(),
                homeCook.getRecipes()
        );
        this.homeCookRepository.updateLastName(homeCook, "CHANGED");
        assertThat(actual.getLastName()).isNotEqualTo(homeCook.getLastName());
    }

    @Test
    @DisplayName("Trying to update the password of user")
    void testChangePassword() {
        HomeCook homeCook = SAMPLE_HOMECOOK;
        var actual = new HomeCook(
                homeCook.getFirstName(),
                homeCook.getLastName(),
                homeCook.getEmail(),
                homeCook.getUsername(),
                homeCook.getPassword(),
                homeCook.getGender(),
                homeCook.getStatus(),
                homeCook.getRecipes()
        );
        this.homeCookRepository.changePassword(homeCook, "CHANGED");
        assertThat(actual.getPassword()).isNotEqualTo(homeCook.getPassword());
    }

    @Test
    @DisplayName("Trying to add new recipe for hommecook")
    void testAddNewRecipeForHomeCook() {
        Recipe recipe = SAMPLE_RECIPE;
        var exeption = assertThrows(InvalidEntityDataException.class,
                () -> homeCookRepository.addNewRecipe(recipe));

        assertEquals(exeption.getMessage(), "This category does not exist");


    }


    public static final HomeCook SAMPLE_HOMECOOK = new HomeCook(
            "Ivan",
            "Ivanov",
            "Ivanov@abv.bg",
            "homecook12",
            "ivanov94",
            Gender.MALE.toString(),
            Status.ACTIVE.toString(),
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

    @Test
    void testAddNewRecipe() {
    }
}

