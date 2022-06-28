package homework.service.impl;


import homework.dao.UserRepositoryJdbc;
import homework.exception.InvalidEntityDataException;
import homework.exception.NonexistingEntityException;
import homework.model.User;
import homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {


    private UserRepositoryJdbc userRepository;
    @Autowired
    public UserServiceImpl(UserRepositoryJdbc userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(User user) {

        return userRepository.create(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NonexistingEntityException(
                        String.format("User with ID='%s' does not exist.", id))
        );
    }

    @Override
    public Optional<User> updateUser(User user) {
        User old = getUserById(user.getId());
        if (!old.getUsername().equals(user.getUsername())) {
            throw new InvalidEntityDataException(
                    String.format("Username '%s' can not be changed to '%s'.",
                            old.getUsername(), user.getUsername()));
        }

        user.setCreated(old.getCreated());
        user.setModified(LocalDateTime.now());

        return userRepository.update(user);
    }

    @Override
    public User deleteUserById(Long id) {
        User old = userRepository.findById(id).orElseThrow(
                () -> new NonexistingEntityException(
                        String.format("User with ID='%s' does not exist.", id)));
        userRepository.deleteById(id);

        return old;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new NonexistingEntityException(
                        String.format("User with Username='%s' does not exist.", username)));

    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

}
