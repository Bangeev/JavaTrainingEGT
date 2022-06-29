package homework.service.impl;


import homework.dao.impl.UserRepositoryMemoryJdbi;
import homework.exception.InvalidEntityDataException;
import homework.exception.PersistenceException;
import homework.model.User;
import homework.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private UserRepositoryMemoryJdbi userRepository;

    @Autowired
    public UserServiceImpl(UserRepositoryMemoryJdbi userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> createUser(User user) {
        var users = userRepository.create(user);
        if(users.getId() > 0) {
            user.setId(users.getId());
            log.info("Successfully created User: {}", user);
            return Optional.of(user);
        }
        log.error("Error creating user: {}", user);
        throw new PersistenceException("Error creating user: " + user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id);
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
        User old = userRepository.findById(id);
        if (old.getId().equals(id)) {
            userRepository.deleteById(id);
        }
        return old;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

}
