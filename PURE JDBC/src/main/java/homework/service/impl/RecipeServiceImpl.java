package homework.service.impl;


import homework.dao.RecipeRepositoryJdbc;
import homework.exception.InvalidEntityDataException;
import homework.exception.NonexistingEntityException;
import homework.model.Recipe;
import homework.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepositoryJdbc recipeRepository;
    @Autowired
    public RecipeServiceImpl(RecipeRepositoryJdbc recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

//    @PostConstruct
//    @Override
//    public void loadData() {
//        if(recipeRepository.count() == 0) {
//            recipeRepository.saveAll(Arrays.asList(MOCK_RECIPES));
//        }
//    }


    @Override
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.create(recipe);
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
    public Optional<Recipe> updateRecipe(Recipe recipe) {
        Recipe old = getRecipeById(recipe.getId());
        if (!old.getName().equals(recipe.getName())) {
            throw new InvalidEntityDataException(
                    String.format("Recipe '%s' can not be changed to '%s'.",
                            old.getName(), recipe.getName()));
        }
        recipe.setCreated(recipe.getCreated());
        recipe.setModified(LocalDateTime.now());

        return recipeRepository.update(recipe);

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
    public Collection<Recipe> getAllRecipesByTags(String tags) {
        return recipeRepository.findAllByTagsIn(tags);
    }
    @Override
    public Collection<Recipe> getAllRecipesByName(String name) {
        return recipeRepository.findAllByNameContains(name);
    }


}
