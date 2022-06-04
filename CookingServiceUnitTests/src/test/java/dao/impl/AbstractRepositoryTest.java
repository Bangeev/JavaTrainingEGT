package dao.impl;

import common.Gender;
import common.Status;
import exception.NoneExistingEntityException;
import model.users.Admins;
import model.users.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static service.impl.AdminsServiceImplTest.SAMPLE_CATEGORY;
import static service.impl.AdminsServiceImplTest.SAMPLE_RECIPE;

class AbstractRepositoryTest {


    AbstractRepository<Long, Users> abstractRepository;

    @BeforeEach
    void setUp() {
        this.abstractRepository = new AbstractRepository<>();

        abstractRepository = new AbstractRepository<>();
        abstractRepository.recipes = Map.ofEntries(
                Map.entry(0L,
                        SAMPLE_RECIPE));
        abstractRepository.allCooking = Map.ofEntries(
                Map.entry(SAMPLE_CATEGORY, List.of(SAMPLE_RECIPE)));

    }

    @Test
    @DisplayName("Creating user with the method from AbstractRepository")
    void testCreatingUser_withAbstractRepo() {

        var user = SAMPLE_USER;

        var actual = abstractRepository.create(user);

        assertAll(
                () -> assertThat(actual).isInstanceOf(Users.class),
                () -> assertThat(actual).isEqualTo(abstractRepository.users.get(actual.getId()))

        );


    }

    @Test
    @DisplayName("Updating user with the method from AbstractRepository")
    void testUpdatingUser_withAbstractRepo() {
        var user = SAMPLE_USER;
        var userCreate = abstractRepository.create(user);
        var actual = abstractRepository.update(userCreate);
        assertAll(
                () -> assertThat(actual).isInstanceOf(Users.class),
                () -> assertThat(actual).isEqualTo(abstractRepository.users.get(actual.getId()))

        );

    }

    @Test
    @DisplayName("Delete user with the method from AbstractRepository")
    void testDeletingUser_withAbstractRepo() throws NoneExistingEntityException {
        var user = SAMPLE_USER;
        abstractRepository.create(user);
        abstractRepository.delete(user.getId());
        assertAll(
                () -> assertTrue(abstractRepository.users.isEmpty())

        );

    }
    @Test
    @DisplayName("Test Throw of exception on delete method from AbstractRepository")
    void testDelete_ThrowsNoneExistingEntityException() {
        // Setup
        var user = SAMPLE_USER;
        var id = user.getId();
        abstractRepository.create(user);

        // Run the test
        assertThatThrownBy(() -> abstractRepository.delete(id))
                .isInstanceOf(NoneExistingEntityException.class);
    }

    @Test
    @DisplayName("Find all users with the method from AbstractRepository")
    void testFindAllUsers_withAbstractRepo() {
        abstractRepository.create(SAMPLE_USER);
        final Collection<Users> result = abstractRepository.findAll();
        assertThat(result).isNotEmpty();

    }

    @Test
    @DisplayName("Find user by his id with the method from AbstractRepository")
    void testFindUserById_withAbstractRepo() {
        var actual = abstractRepository.create(SAMPLE_USER);
        var id = actual.getId();
        final var result = abstractRepository.findById(id);
        assertThat(result).isInstanceOf(Admins.class)
                .extracting("username")
                .isEqualTo(actual.getUsername());
    }



    private static final Admins SAMPLE_USER = new Admins("Georgi",
            "Bangeev",
            "bangeev13@gmail.com",
            "bangeev",
            "bangeev",
            Gender.MALE.name(),
            Status.ACTIVE.name(),
            new ArrayList<>(),
            new ArrayList<>());

}