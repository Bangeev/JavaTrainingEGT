package homework.dao;



import homework.model.Recipe;
import homework.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RecipeRepositoryJdbc extends RepositoryJdbc<Recipe> {
    Collection<Recipe> findAllByTagsIn(String tags);
    Collection<Recipe> findAllByNameContains(String name);

}
