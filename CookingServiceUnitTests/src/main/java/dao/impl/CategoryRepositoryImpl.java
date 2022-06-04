package dao.impl;

import category.Category;
import dao.CategoryRepository;

import java.util.HashMap;
import java.util.Map;

public class CategoryRepositoryImpl extends AbstractRepository<Long, Category> implements CategoryRepository {

private static long nextId = 0;
private final Map<Long, Category> categories = new HashMap<>();
//логиката е преместена

}
