package dao;

import exception.InvalidEntityDataException;
import model.recipe.Recipe;
import model.users.HomeCook;

public interface HomeCookRepository extends Repository<Long, HomeCook> {

    void updateFirstName(HomeCook homeCook,String value);
    void updateLastName(HomeCook homeCook, String value);
    void changePassword(HomeCook homeCook, String value);
    void addNewRecipe(Recipe recipe) throws InvalidEntityDataException;;


}
