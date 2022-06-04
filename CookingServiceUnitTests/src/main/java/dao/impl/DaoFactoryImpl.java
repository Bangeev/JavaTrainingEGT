package dao.impl;

import dao.*;

public class DaoFactoryImpl implements DaoFactory {
    @Override
    public RecipeRepository createRecipeRepository() {
        return new RecipeRepositoryMemoryImpl();
    }

    @Override
    public AdminRepository createAdminRepository() {
        return new AdminRepositoryImpl();
    }

    @Override
    public HomeCookRepository createHomeCookRepository() {
        return new HomeCookRepositoryImpl();
    }

    @Override
    public CategoryRepository createCategoryRepository() {
        return new CategoryRepositoryImpl();
    }
}
