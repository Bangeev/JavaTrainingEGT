package dao.impl;

import dao.HomeCookRepository;
import exception.InvalidEntityDataException;
import model.recipe.Recipe;
import model.users.HomeCook;

public class HomeCookRepositoryImpl extends AbstractRepository<Long, HomeCook> implements HomeCookRepository {
    @Override
    public void updateFirstName(HomeCook homeCook, String value) {
        homeCook.setFirstName(value);
    }

    @Override
    public void updateLastName(HomeCook homeCook, String value) {
        homeCook.setLastName(value);
    }

    @Override
    public void changePassword(HomeCook homeCook, String value) {
        homeCook.setPassword(value);
    }

    @Override
    public void addNewRecipe(Recipe recipe) throws InvalidEntityDataException {

        if (allCooking.containsKey(recipe.getCategory())) {
            allCooking.get(recipe.getCategory()).add(recipe);

        }else{
            throw new InvalidEntityDataException("This category does not exist");
        }
    }
}
