package dao.impl;

import category.Category;
import dao.AdminRepository;
import model.recipe.Recipe;
import model.users.Admins;
import model.users.Users;

import java.util.ArrayList;
import java.util.List;

public class AdminRepositoryImpl extends AbstractRepository<Long, Admins>  implements AdminRepository {

    private List<Recipe> recipes = new ArrayList<>(); // Ne sum siguren

    @Override
    public void changeUserData(Users user, String entity, String value) {

        switch (entity) {
            case "first name":
                user.setFirstName(value);
                break;
            case "last name":
                user.setLastName(value);
                break;
            case "username":
                user.setUsername(value);
                break;
            case "gender":
                user.setGender(value);
                break;
            case "status":
                user.setStatus(value);
                break;
        }
    }


    @Override
    public void addNewCategories(Category category) {

allCooking.putIfAbsent(category, new ArrayList<>());
    }

    @Override
    public void addNewRecipes(Recipe recipe) {
allCooking.get(recipe.getCategory()).add(recipe);
    }
}
