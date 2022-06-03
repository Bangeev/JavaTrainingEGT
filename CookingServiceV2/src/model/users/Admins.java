package model.users;

import category.Category;
import common.Role;
import model.recipe.Recipe;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Admins extends Users{
    private List<Category> categoriesModerate = new ArrayList<>();

    public Admins(String firstName, String lastName, String email, String username, String password,
                  String gender, String status,
                  List<Recipe> recipes, List<Category> categoriesModerate) {

        super(firstName, lastName, email, username, password, gender, Role.ADMIN, status, recipes);
        this.categoriesModerate = categoriesModerate;
    }

    public Admins() {

    }

    public List<Category> getCategoriesModerate() {
        return categoriesModerate;
    }

    public void setCategoriesModerate(List<Category> categoriesModerate) {
        this.categoriesModerate = categoriesModerate;
    }

    public String showAllRecipes(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Recipes - ")
                .append(System.lineSeparator());
        getRecipes().forEach(recipe -> stringBuilder.append(recipe).append(System.lineSeparator()));
        return stringBuilder.toString();
    }

    public void editRecipes(){
        setModified(LocalDate.now());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Admins - ");
        sb.append(super.toString());
        sb.append("categoriesModerate=").append(categoriesModerate);
        sb.append(' ');
        return sb.toString();
    }
}
