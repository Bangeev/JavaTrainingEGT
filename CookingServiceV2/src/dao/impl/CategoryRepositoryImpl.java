package dao.impl;

import category.Category;
import dao.CategoryRepository;

import java.util.HashMap;
import java.util.Map;

public class CategoryRepositoryImpl extends AbstractRepository<Long, Category> implements CategoryRepository {

private static long nextId = 0;
private Map<Long, Category> categories = new HashMap<>();

}
