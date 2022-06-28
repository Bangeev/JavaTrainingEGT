package homework.dao;



import homework.model.User;

import java.util.Optional;

public interface UserRepositoryJdbc extends RepositoryJdbc<User> {
    Optional<User> findByUsername(String username);

}
