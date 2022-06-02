package service;

import category.Category;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;
import model.users.Admins;
import model.users.Users;

import java.util.Collection;

public interface AdminsService {
    Collection<Admins> getAllAdmins();
    Admins addAdmin(Admins admin) throws InvalidEntityDataException;
    Admins updateAdmin(Admins admins) throws NoneExistingEntityException;
    Admins deleteAdminById(Long id) throws NoneExistingEntityException;
    void addNewCategory(Category category);
    void addNewRecipe(Recipe recipe);
    void changeUserData(Users user, String entity, String value);

}
