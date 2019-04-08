package com.aimprosoft.sandbox.util.validator;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.configuration.annotation.BeanValidationAnnotationsConfigurer;
import net.sf.oval.integration.spring.BeanInjectingCheckInitializationListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BaLiK on 01.04.19
 */
@Component
public class OvalValidator {
    private static Logger LOG = LogManager.getLogger(OvalValidator.class);

    public boolean validateId(final String idStr) {
        if (idStr == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(DataPatterns.ID_PATTERN);
        Matcher matcher = pattern.matcher(idStr);

        return matcher.matches();
    }

    private net.sf.oval.Validator validator;

    public OvalValidator() {
        AnnotationsConfigurer myConfigurer = new AnnotationsConfigurer();
        myConfigurer.addCheckInitializationListener(BeanInjectingCheckInitializationListener.INSTANCE);
        validator = new Validator(myConfigurer, new BeanValidationAnnotationsConfigurer());
    }

    public boolean validate(Object object) {
        List<ConstraintViolation> violations = validator.validate(object);
        return violations.isEmpty();
    }

    public List<String> getErrors(Object object) {
        List<ConstraintViolation> errors = validator.validate(object);
        List<String> messages = new ArrayList<>();

        for (ConstraintViolation error : errors) {
            LOG.error("Oval validation error: {}", error.getMessage());
            messages.add(error.getMessage());
        }

        return messages;
    }
}
