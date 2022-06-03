package service.impl;

import category.Category;
import dao.AdminRepository;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;
import model.users.Admins;
import model.users.Users;
import service.AdminsService;

import java.time.LocalDate;
import java.util.Collection;

public class AdminsServiceImpl implements AdminsService {

    private AdminRepository adminRepository;
    private static final String VALID_EMAIL_PATTERN = "/^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$/gm";
    private static final String INVALID_WORD_PATTERN = "/\\W/gm";
    private static final String VALID_PASSWORD_PATTERN = "/(?=^.{8,15}$)((?!.*\\s)(?=.*[A-Z]+)(?=.*[a-z]+))(?=(1)(?=.*\\d)|.*[^A-Za-z0-9]+)^.*$/";


    public AdminsServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Collection<Admins> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admins addAdmin(Admins admin) throws InvalidEntityDataException {
        if (admin.getFirstName().trim().length() < 2 || admin.getFirstName().trim().length() > 15) {
            throw new InvalidEntityDataException(
                    "Administrator First Name length should be between 2 and 15 characters.");
        }

        if (admin.getLastName().trim().length() < 2 || admin.getLastName().trim().length() > 15) {
            throw new InvalidEntityDataException(
                    "Administrator Last Name length should be between 2 and 15 characters.");
        }

        if (admin.getEmail().matches(VALID_EMAIL_PATTERN)) {
            throw new InvalidEntityDataException(
                    "Administrator email is not valid");
        }

        if (admin.getUsername().matches(INVALID_WORD_PATTERN)) {
            throw new InvalidEntityDataException(
                    "Administrator username should be only with word characters.");
        }

        if (admin.getUsername().length() < 2 || admin.getUsername().length() > 15) {
            throw new InvalidEntityDataException(
                    "Administrator username length should be between 2 and 15 characters.");
        }

        if (admin.getPassword().matches(VALID_PASSWORD_PATTERN)) {
            throw new InvalidEntityDataException(
                    "Administrator password contains at least one digit,one capital letter,and one sign" +
                            "different than letter or digit .");
        }


        if (admin.getPassword().length() < 8 || admin.getPassword().length() > 15) {
            throw new InvalidEntityDataException(
                    "Administrator password length should be between 8 and 15 characters."
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

