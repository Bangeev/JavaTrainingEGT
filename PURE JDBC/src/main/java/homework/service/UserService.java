package homework.service;

import homework.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);
    User getUserById(Long id);
    Optional<User> updateUser(User user);
    User deleteUserById(Long id);
    User getUserByUsername(String username);
    Collection<User> getAllUsers();





}
