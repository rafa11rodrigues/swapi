package com.eleflow.swapi.infrastructure.domain.validation;


import com.eleflow.swapi.infrastructure.exception.ConstraintViolationListException;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class BusinessValidator {

    private static final ValidatorFactory validatorFactory;
    private static final Validator validator;

    static {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public static <T> void apply(T object) {
        var errors = validator.validate(object);

        if (!errors.isEmpty()) {
            throw new ConstraintViolationListException(errors);
        }
    }
}
