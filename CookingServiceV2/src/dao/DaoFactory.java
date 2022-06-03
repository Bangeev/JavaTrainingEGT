package dao;



public interface DaoFactory {

    RecipeRepository createRecipeRepository();

    AdminRepository createAdminRepository();

    HomeCookRepository createHomeCookRepository();

    CategoryRepository createCategoryRepository();


}
