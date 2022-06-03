package service.impl;

import dao.HomeCookRepository;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;
import model.users.HomeCook;
import service.HomeCookService;

import java.time.LocalDate;
import java.util.Collection;

public class HomeCookServiceImpl implements HomeCookService {

    private HomeCookRepository homeCookRepository;
    private static final String VALID_EMAIL_PATTERN = "/^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$/gm";
    private static final String INVALID_WORD_PATTERN = "/\\W/gm";
    private static final String VALID_PASSWORD_PATTERN = "/(?=^.{8,15}$)((?!.*\\s)(?=.*[A-Z]+)(?=.*[a-z]+))(?=(1)(?=.*\\d)|.*[^A-Za-z0-9]+)^.*$/";

    public HomeCookServiceImpl(HomeCookRepository homeCookRepository) {
        this.homeCookRepository = homeCookRepository;
    }


    @Override
    public Collection<HomeCook> getAllHomeCooks() {
        return homeCookRepository.findAll();
    }

    @Override
    public HomeCook addHomeCook(HomeCook homeCook) throws InvalidEntityDataException {
        if(homeCook.getFirstName().trim().length() < 2 || homeCook.getFirstName().trim().length() > 15){
            throw new InvalidEntityDataException(
                    "HomeCook First Name length should be between 2 and 15 characters.");
        }

        if(homeCook.getLastName().trim().length() < 2 || homeCook.getLastName().trim().length() > 15){
            throw new InvalidEntityDataException(
                    "HomeCook Last Name length should be between 2 and 15 characters.");
        }

        if(homeCook.getEmail().matches(VALID_EMAIL_PATTERN)){
            throw new InvalidEntityDataException(
                    "HomeCook email is not valid");
        }

        if(homeCook.getUsername().matches(INVALID_WORD_PATTERN)){
            throw  new InvalidEntityDataException(
                    "HomeCook username should be only with word characters.");
        }

        if (homeCook.getUsername().length() < 2 || homeCook.getUsername().length() > 15){
            throw  new InvalidEntityDataException(
                    "HomeCook username length should be between 2 and 15 characters.");
        }

        if(homeCook.getPassword().matches(VALID_PASSWORD_PATTERN)){
            throw  new InvalidEntityDataException(
                    "HomeCook password contains at least one digit,one capital letter,and one sign" +
                            "different than letter or digit .");
        }



        if(homeCook.getPassword().length() < 8 || homeCook.getPassword().length() > 15){
            throw new InvalidEntityDataException(
                    "HomeCook password length should be between 8 and 15 characters."
            );
        }


        return homeCookRepository.create(homeCook);
    }

    @Override
    public HomeCook updateHomeCook(HomeCook homeCook) throws NoneExistingEntityException {
        homeCook.setModified(LocalDate.now());
        return homeCookRepository.update(homeCook);
    }

    @Override
    public HomeCook deleteHomeCookById(Long id) throws NoneExistingEntityException {
        return homeCookRepository.delete(id);
    }

    @Override
    public void updateFirstName(HomeCook homeCooks, String value) {
        homeCookRepository.updateFirstName(homeCooks, value);
    }

    @Override
    public void updateLastName(HomeCook homeCooks, String value) {
        homeCookRepository.updateLastName(homeCooks,value);
    }

    @Override
    public void changePassword(HomeCook homeCooks, String value) {
        homeCookRepository.changePassword(homeCooks,value);
    }

    @Override
    public void addNewRecipe(Recipe recipe) throws InvalidEntityDataException {
        homeCookRepository.addNewRecipe(recipe);
    }
}
