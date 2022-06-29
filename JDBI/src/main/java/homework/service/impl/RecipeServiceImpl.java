package homework.service.impl;


import homework.dao.RecipeRepositoryJdbi;
import homework.exception.InvalidEntityDataException;
import homework.exception.NonexistingEntityException;
import homework.exception.PersistenceException;
import homework.model.Recipe;
import homework.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepositoryJdbi recipeRepository;
    @Autowired
    public RecipeServiceImpl(RecipeRepositoryJdbi recipeRepository) {
        this.recipeRepository = recipeRepository;
    }




    @Override
    public Recipe createRecipe(Recipe recipe) {
        long id = recipeRepository.create(recipe);
        if(id > 0) {
            recipe.setId(id);
            log.info("Successfully created Recipe: {}", recipe);
            return recipe;
        }
        log.error("Error creating recipe: {}", recipe);
        throw new PersistenceException("Error creating recipe: " + recipe);
    }

    @Override
    public Collection<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(
                () -> new NonexistingEntityException(
                        String.format("Recipe with ID='%s' does not exist.", id))
        );
        return recipe;

    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        Recipe old = getRecipeById(recipe.getId());
        if (!old.getName().equals(recipe.getName())) {
            throw new InvalidEntityDataException(
                    String.format("Recipe '%s' can not be changed to '%s'.",
                            old.getName(), recipe.getName()));
        }
        recipe.setCreated(recipe.getCreated());
        recipe.setModified(LocalDateTime.now());
        if(recipeRepository.update(recipe)) {
            log.info("Successfully updated Recipe: {}", recipe);
            return recipe;

        }
        log.error("Error updating recipe: {}", recipe);
        throw new InvalidEntityDataException(String.format("Error updating recipe '%s'", recipe.getName()));
    }

    @Override
    public Recipe deleteRecipe(Long id) {
        Recipe old = recipeRepository.findById(id).orElseThrow(
                () -> new NonexistingEntityException(
                        String.format("Recipe with ID='%s' does not exist.", id)));
        recipeRepository.deleteById(id);

        return old;
    }

    @Override
    public List<Recipe> addRecipesBatch(List<Recipe> recipes) {
        long[] ids = recipeRepository.createBatch(recipes);
        if (Arrays.stream(ids).allMatch(i -> i > 0)) {
            for(int i = 0; i < ids.length; i++) {
                recipes.get(i).setId(ids[i]);
            }
            log.info("Successfully created {} recipes in batch.", recipes.size());
            return recipes;
        }
        log.error("Error creating recipes in batch: {}", recipes);
        throw new PersistenceException("Error creating recipes in batch: " + recipes);
    }



    @Override
    public Collection<Recipe> getAllRecipesByTags(String tags) {
        return recipeRepository.findAllByTagsIn(tags);
    }
    @Override
    public Collection<Recipe> getAllRecipesByName(String name) {
        return recipeRepository.findAllByNameContains(name);
    }


}
