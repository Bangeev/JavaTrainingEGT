package dao.impl;

import category.Category;
import dao.Identifiable;
import dao.Repository;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<K, V extends Identifiable<K>> implements Repository<K, V> {

    private static long nextId = 0;
    public Map<K, V> users = new HashMap<>();
    Map<Long, Recipe> recipes = new HashMap<>();
    Map<Category, List<Recipe>> allCooking = new HashMap<>();


    @Override
    public V create(V entity) {

        entity.setId(++nextId);
        users.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public V update(V entity) {

        V oldEntity = findById(entity.getId());
        if (oldEntity == null) {
            throw new IllegalStateException("Invalid Entity");
        }
        users.put(entity.getId(), entity);
        return entity;


    }

    @Override
    public V delete(K id) throws NoneExistingEntityException {
        V oldEntity = users.remove(id);
        if (oldEntity == null) {
            throw new NoneExistingEntityException("The user with ID - " + id + "does not exist");
        }
        return oldEntity;
    }

    @Override
    public Collection<V> findAll() {
        return users.values();
    }

    @Override
    public V findById(K id) {
        return users.get(id);
    }
}
