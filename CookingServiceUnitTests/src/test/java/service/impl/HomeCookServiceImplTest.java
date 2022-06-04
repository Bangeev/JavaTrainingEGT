package service.impl;

import common.Gender;
import common.Status;
import dao.HomeCookRepository;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;
import model.users.HomeCook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import util.HomeCookValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static service.impl.AdminsServiceImplTest.SAMPLE_RECIPE;

class HomeCookServiceImplTest {
    @Mock
    private HomeCookRepository homeCookRepository;
    @Mock
    private HomeCookValidator homeCookValidator;

    private HomeCookServiceImpl homeCookService;


    @BeforeEach
    void setUp() {
        homeCookRepository = Mockito.mock(HomeCookRepository.class);
        homeCookValidator = Mockito.mock(HomeCookValidator.class);
        homeCookService = new HomeCookServiceImpl(homeCookRepository, homeCookValidator);
    }

    @Test
    @DisplayName("Trying to fetch all users that are hoomecoks")
    void testGetAllHomeCooks() {


            final Collection<HomeCook> expectedResult = SAMPLE_HOMECOOK_COLLECTION;


            final Collection<HomeCook> homeCooks = SAMPLE_HOMECOOK_COLLECTION;
            when(homeCookRepository.findAll()).thenReturn(homeCooks);


            final Collection<HomeCook> result = homeCookService.getAllHomeCooks();


            assertThat(result).isEqualTo(expectedResult);

    }
    @Test
    @DisplayName("Trying to fetch all users that are hoomecoks but therea are null")
    void testGetAllHomeCooks_HomeCookRepositoryReturnsNoItems() {

        when(homeCookRepository.findAll()).thenReturn(Collections.emptyList());


        final Collection<HomeCook> result = homeCookService.getAllHomeCooks();


        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("Trying to add new user to homecook")
    void testAddHomeCook() throws InvalidEntityDataException, ConstraintViolationException {
        final HomeCook homeCook = SAMPLE_HOMECOOK;
        final HomeCook expectedResult = SAMPLE_HOMECOOK;

        final HomeCook homeCook1 = SAMPLE_HOMECOOK;
        when(homeCookRepository.create(
               SAMPLE_HOMECOOK)).thenReturn(homeCook1);

        final HomeCook result = homeCookService.addHomeCook(homeCook);


        assertThat(result).isEqualTo(expectedResult);
        verify(homeCookValidator).validate(SAMPLE_HOMECOOK);
    }
    @Test
    @DisplayName("Trying to fetch all users that are hoomecoks but there is a exception")
    void testAddHomeCook_GenericValidatorThrowsInvalidEntityDataException() throws Exception {

        final HomeCook homeCook = SAMPLE_HOMECOOK;
        doThrow(InvalidEntityDataException.class).when(homeCookValidator).validate(SAMPLE_HOMECOOK);


        final HomeCook homeCook1 = SAMPLE_HOMECOOK;
        when(homeCookRepository.create(SAMPLE_HOMECOOK)).thenReturn(homeCook1);


        assertThatThrownBy(() -> homeCookService.addHomeCook(homeCook))
                .isInstanceOf(InvalidEntityDataException.class);
    }
    @Test
    @DisplayName("Trying to update user that is homecook")
    void testUpdateHomeCook() throws NoneExistingEntityException {
        final HomeCook homeCook = SAMPLE_HOMECOOK;
        final HomeCook expectedResult = SAMPLE_HOMECOOK;

        final HomeCook homeCook1 = SAMPLE_HOMECOOK;
        when(homeCookRepository.update(homeCook)).thenReturn(homeCook1);

        final HomeCook result = homeCookService.updateHomeCook(homeCook);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Trying to fetch all exception from using updatehomecook")
    void testUpdateHomeCook_ThrowsNoneExistingEntityException() {

        final HomeCook homeCook = SAMPLE_HOMECOOK;
        final HomeCook homeCook1 = SAMPLE_HOMECOOK;
        when(homeCookRepository.update(SAMPLE_HOMECOOK)).thenReturn(homeCook1);


        assertDoesNotThrow(() -> homeCookService.updateHomeCook(homeCook));

//        assertThatThrownBy(() -> homeCookService.updateHomeCook(homeCook))
//                .isInstanceOf(NoneExistingEntityException.class);
//        assertThatExceptionOfType(NoneExistingEntityException.class)
//                .isThrownBy(() -> homeCookService.updateHomeCook(homeCook))
//        .withMessageStartingWith("INVALID_USER_DATA_FOR_USER");
//        assertThrows(NoneExistingEntityException.class,
//                () -> homeCookService.updateHomeCook(homeCook));

    }

    @Test
    @DisplayName("Trying to delete homecook by ising his id")
    void testDeleteHomeCookById() throws NoneExistingEntityException {
        final HomeCook homeCook = SAMPLE_HOMECOOK;
        final HomeCook expectedResult = SAMPLE_HOMECOOK;
        when(homeCookRepository.delete(0L)).thenReturn(homeCook);


        final HomeCook result = homeCookService.deleteHomeCookById(0L);


        assertThat(result).isEqualTo(expectedResult);
    }
    @Test
    @DisplayName("Trying to fetch exeption for using delete by id hoomecook")
    void testDeleteHomeCookById_HomeCookRepositoryThrowsNoneExistingEntityException() throws Exception {

        when(homeCookRepository.delete(0L)).thenThrow(NoneExistingEntityException.class);


        assertThatThrownBy(() -> homeCookService.deleteHomeCookById(0L))
                .isInstanceOf(NoneExistingEntityException.class);
    }

    @Test
    @DisplayName("Trying to update first name of homecook")
    void testUpdateFirstName() {
        final HomeCook homeCooks = SAMPLE_HOMECOOK;
        homeCookService.updateFirstName(homeCooks, "value");


        verify(homeCookRepository).updateFirstName(SAMPLE_HOMECOOK, "value");
    }

    @Test
    @DisplayName("Trying to update last name of homecook")
    void testUpdateLastName() {
        final HomeCook homeCooks = SAMPLE_HOMECOOK;
        homeCookService.updateLastName(homeCooks, "value");


        verify(homeCookRepository).updateLastName(SAMPLE_HOMECOOK, "value");
    }

    @Test
    @DisplayName("Trying to update password of homecook")
    void testChangePassword() {
        final HomeCook homeCooks = SAMPLE_HOMECOOK;
        homeCookService.changePassword(homeCooks, "value");


        verify(homeCookRepository).changePassword(SAMPLE_HOMECOOK, "value");
    }

    @Test
    @DisplayName("Trying to add new recipe for homecook")
    void testAddNewRecipeForHomeCook() throws InvalidEntityDataException {
        final Recipe recipe = SAMPLE_RECIPE;


        homeCookService.addNewRecipe(recipe);


        verify(homeCookRepository).addNewRecipe(SAMPLE_RECIPE);
    }
    @Test
    @DisplayName("Trying to fetch exception for using addnew recipe homecook")
    void testAddNewRecipe_HomeCookRepositoryThrowsInvalidEntityDataException() throws Exception {

        final Recipe recipe = SAMPLE_RECIPE;
                doThrow(InvalidEntityDataException.class).when(homeCookRepository).addNewRecipe(SAMPLE_RECIPE);


        assertThatThrownBy(() -> homeCookService.addNewRecipe(recipe))
                .isInstanceOf(InvalidEntityDataException.class);
    }

    public static final Collection<HomeCook> SAMPLE_HOMECOOK_COLLECTION = List.of(new HomeCook("Ivan", "Ivanov", "Ivanov@abv.bg", "homecook12"
            , "ivanov94", Gender.MALE.toString(), Status.ACTIVE.toString(), new ArrayList<>()));
    public static final HomeCook SAMPLE_HOMECOOK = new HomeCook("Ivan", "Ivanov", "Ivanov@abv.bg", "homecook12"
            , "ivanov94", Gender.MALE.toString(), Status.ACTIVE.toString(), new ArrayList<>());
}