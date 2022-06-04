package dao;

import model.recipe.Recipe;

public interface RecipeRepository extends Repository<Long, Recipe> {

    String findBySearchingInCategory();
    Recipe findBySearching(String title,String products,String shortDescription, String tags);
    void filterByCookingTime();

}
