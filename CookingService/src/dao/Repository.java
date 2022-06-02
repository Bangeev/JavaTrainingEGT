package dao;

import exception.NoneExistingEntityException;

import java.util.Collection;

public interface Repository<K,V extends Identifiable<K>> {


    V create(V entity);
    V update(V entity);
    V delete(K id) throws NoneExistingEntityException;
    Collection<V> findAll();
    V findById(K id);

}
