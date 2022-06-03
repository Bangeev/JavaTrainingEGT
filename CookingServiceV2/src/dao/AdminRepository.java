package dao;

import category.Category;
import model.recipe.Recipe;
import model.users.Admins;
import model.users.Users;

public interface AdminRepository extends Repository<Long, Admins>{
    void changeUserData(Users user, String entity, String value);
    void addNewCategories(Category category);
    void addNewRecipes(Recipe recipe);
}
