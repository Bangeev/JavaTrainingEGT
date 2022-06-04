package util;

import dao.HomeCookRepository;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import model.users.HomeCook;

public class HomeCookValidator implements GenericValidator<HomeCook> {
    private HomeCookRepository homeCookRepository;
    private static final String VALID_EMAIL_PATTERN = "/^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$/gm";
    private static final String INVALID_WORD_PATTERN = "/\\W/gm";
    private static final String VALID_PASSWORD_PATTERN = "/(?=^.{8,15}$)((?!.*\\s)(?=.*[A-Z]+)(?=.*[a-z]+))(?=(1)(?=.*\\d)|.*[^A-Za-z0-9]+)^.*$/";


    @Override
    public void validate(HomeCook homeCook) throws ConstraintViolationException, InvalidEntityDataException {

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

    }
}
