package com.aimprosoft.sandbox.validator;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author BaLiK on 28.03.19
 */
public class HibernateValidator {
    @Inject
    Validator validator;

    private static HibernateValidator instance = null;

    private HibernateValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static HibernateValidator getInstance() {
        if (instance == null) {
            instance = new HibernateValidator();
        }

        return instance;
    }

    public boolean isValidate(Object object) {
        return validator.validate(object).isEmpty();
    }

    public Set<ConstraintViolation<Object>> getMessages(Object object) {
        return validator.validate(object);
    }
}
