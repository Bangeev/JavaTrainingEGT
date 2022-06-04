package util;

import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;

public interface GenericValidator<T> {
    void validate(T entity) throws ConstraintViolationException, InvalidEntityDataException;
}
