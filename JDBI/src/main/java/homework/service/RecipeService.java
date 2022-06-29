package homework.service;

import homework.model.Recipe;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RecipeService {

    Recipe createRecipe(Recipe recipe);
    Collection<Recipe> getAllRecipes();
    Recipe getRecipeById(Long id);
    Recipe updateRecipe(Recipe recipe);
    Recipe deleteRecipe(Long id);
    List<Recipe> addRecipesBatch(List<Recipe> recipes);
    Collection<Recipe> getAllRecipesByTags(String tags);
    Collection<Recipe> getAllRecipesByName(String name);
}
