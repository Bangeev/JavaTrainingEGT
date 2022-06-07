package service.impl;

import category.Category;
import dao.AdminRepository;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;
import model.users.Admins;
import model.users.Users;
import service.AdminsService;
import util.AdminValidator;
import util.GenericValidator;

import java.time.LocalDate;
import java.util.Collection;

public class AdminsServiceImpl implements AdminsService {

    private AdminRepository adminRepository;
    private GenericValidator<Admins> genericValidator;
    private Users user;





    public AdminsServiceImpl(AdminRepository adminRepository, GenericValidator<Admins> genericValidator) {
        this.adminRepository = adminRepository;
        this.genericValidator = genericValidator;
    }

    @Override
    public Collection<Admins> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admins addAdmin(Admins admin) throws InvalidEntityDataException {


        try {
           genericValidator.validate(admin);
        } catch (ConstraintViolationException e) {
            throw new InvalidEntityDataException(
                    String.format("Invalid user data for user '%s'.", admin.getUsername()),
                    e
            );
        }

        return adminRepository.create(admin);
    }

    @Override
    public Admins updateAdmin(Admins admins) throws NoneExistingEntityException {
        admins.setModified(LocalDate.now());
        return adminRepository.update(admins);
    }

    @Override
    public Admins deleteAdminById(Long id) throws NoneExistingEntityException {
        return adminRepository.delete(id);
    }

    @Override
    public void addNewCategory(Category category) {
        adminRepository.addNewCategories(category);
    }

    @Override
    public void addNewRecipe(Recipe recipe) {
        adminRepository.addNewRecipes(recipe);
    }

    @Override
    public void changeUserData(Users user, String entity, String value) {
        adminRepository.changeUserData(user, entity, value);
    }
}

