package dao;

import exception.NoneExistingEntityException;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

public interface Repository<K,V extends Identifiable<K>> {


    V create(V entity);
    V update(V entity);
    V delete(K id) throws NoneExistingEntityException;
    Collection<V> findAll();
    V findById(K id);


}
