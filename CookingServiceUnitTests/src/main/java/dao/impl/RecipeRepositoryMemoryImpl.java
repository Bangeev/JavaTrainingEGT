package dao.impl;

import category.Category;
import dao.RecipeRepository;
import model.recipe.Recipe;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RecipeRepositoryMemoryImpl extends AbstractRepository<Long, Recipe> implements RecipeRepository {

    private static long nextId = 0;
    @Override
    public Recipe create(Recipe recipe) {
        recipe.setId(++nextId);
        recipes.put(recipe.getId(),recipe);
        return recipe;
    }

    @Override
    public String findBySearchingInCategory() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Category, List<Recipe>> s : allCooking.entrySet()) {
            sb.append(s.getKey())
                    .append(System.lineSeparator()).append(" - ").append(s.getValue())
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public Recipe findBySearching(String title, String products, String shortDescription, String tags) {
        Recipe findRecipe = new Recipe();
        for (Recipe recipe : recipes.values()) {
            if (title.equals(recipe.getTitle()) && products.equals(recipe.getUsedProducts())
                    && shortDescription.equals(recipe.getShortDescription()) && tags.equals(recipe.getTags())) {
                findRecipe = recipe;
            }
        }
        return findRecipe;
    }

    @Override
    public void filterByCookingTime() {
        recipes.entrySet().stream()
                .sorted(Comparator.comparing(o -> o.getValue().getCookingTime()))
                .forEach(System.out::println);

    }
}
