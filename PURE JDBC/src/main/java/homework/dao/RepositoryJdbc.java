package homework.dao;

import java.util.Collection;
import java.util.Optional;

public interface RepositoryJdbc<T> {
    T create(T entity);

    Optional<T> update(T entity);

    Optional<T> findById(Long id);

    Collection<T> findAll();

    Optional<T> deleteById(Long id);


}
