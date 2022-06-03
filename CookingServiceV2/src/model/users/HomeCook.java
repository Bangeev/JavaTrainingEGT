package model.users;

import common.Role;
import model.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class HomeCook extends Users {

    private List<Recipe> favoriteRecipes = new ArrayList<>();
    private List<HomeCook> favoriteCooks = new ArrayList<>();

    public HomeCook() {

    }

    public HomeCook(String firstName, String lastName, String email, String username, String password, String gender, String status, List<Recipe> recipes) {
        super(firstName, lastName, email, username, password, gender, Role.HOME_COOK, status, recipes);
    }

    public HomeCook(String firstName, String lastName, String email, String username, String password, String gender, String status, List<Recipe> recipes, List<Recipe> favoriteRecipes, List<HomeCook> favoriteCooks) {
        super(firstName, lastName, email, username, password, gender, Role.HOME_COOK, status, recipes);
        this.favoriteRecipes = favoriteRecipes;
        this.favoriteCooks = favoriteCooks;
    }

    public List<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    public List<HomeCook> getFavoriteCooks() {
        return favoriteCooks;
    }

    public void setFavoriteCooks(List<HomeCook> favoriteCooks) {
        this.favoriteCooks = favoriteCooks;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", HomeCook.class.getSimpleName() + " ", " ")
                .add(super.toString())
                .add("favoriteRecipes=" + favoriteRecipes)
                .add("favoriteCooks=" + favoriteCooks)
                .toString();
    }
}
