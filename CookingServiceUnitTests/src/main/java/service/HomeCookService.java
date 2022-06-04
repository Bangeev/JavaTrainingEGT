package service;

import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;
import model.users.HomeCook;

import java.util.Collection;

public interface HomeCookService {
    Collection<HomeCook> getAllHomeCooks();
    HomeCook addHomeCook(HomeCook admin) throws InvalidEntityDataException;
    HomeCook updateHomeCook(HomeCook admin) throws NoneExistingEntityException;
    HomeCook deleteHomeCookById(Long id) throws NoneExistingEntityException;
    void updateFirstName(HomeCook homeCooks, String value);
    void updateLastName(HomeCook homeCooks, String value);
    void changePassword(HomeCook homeCooks ,String value);
    void addNewRecipe(Recipe recipe) throws InvalidEntityDataException;
}
