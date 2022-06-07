package service.impl;

import dao.HomeCookRepository;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import exception.NoneExistingEntityException;
import model.recipe.Recipe;
import model.users.HomeCook;
import service.HomeCookService;
import util.GenericValidator;

import java.time.LocalDate;
import java.util.Collection;

public class HomeCookServiceImpl implements HomeCookService {

    private HomeCookRepository homeCookRepository;
    private GenericValidator<HomeCook> genericValidator;

    public HomeCookServiceImpl(HomeCookRepository homeCookRepository, GenericValidator<HomeCook> genericValidator) {
        this.homeCookRepository = homeCookRepository;
        this.genericValidator = genericValidator;
    }


    @Override
    public Collection<HomeCook> getAllHomeCooks() {
        return homeCookRepository.findAll();
    }

    @Override
    public HomeCook addHomeCook(HomeCook homeCook) throws InvalidEntityDataException {

        try {
            genericValidator.validate(homeCook);
        } catch (ConstraintViolationException e) {
            throw new InvalidEntityDataException(
                    String.format("Invalid homecook data for homecook '%s'.", homeCook.getUsername()),
                    e
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
