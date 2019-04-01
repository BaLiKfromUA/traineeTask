package com.aimprosoft.sandbox.util.validator;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.configuration.annotation.BeanValidationAnnotationsConfigurer;

import java.util.List;

/**
 * @author BaLiK on 01.04.19
 */
//todo:log
public class OvalValidator {
    private static OvalValidator instance = null;
    private net.sf.oval.Validator validator;

    private OvalValidator() {
        validator = new Validator(new AnnotationsConfigurer(), new BeanValidationAnnotationsConfigurer());
    }

    public static OvalValidator getInstance() {
        if (instance == null) {
            instance = new OvalValidator();
        }

        return instance;
    }

    public boolean validate(Object object) {
        List<ConstraintViolation> violations = validator.validate(object);

        return violations.isEmpty();
    }

}
