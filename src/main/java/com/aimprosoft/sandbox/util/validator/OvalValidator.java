package com.aimprosoft.sandbox.util.validator;

import com.aimprosoft.sandbox.controller.data.EmployeeData;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.configuration.annotation.BeanValidationAnnotationsConfigurer;
import net.sf.oval.integration.spring.BeanInjectingCheckInitializationListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BaLiK on 01.04.19
 */
@Component
//todo:validate
public class OvalValidator implements org.springframework.validation.Validator {
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


    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public void validate(Object object, Errors errors) {
        List<ConstraintViolation> violations = validator.validate(object);

        for (ConstraintViolation error : violations) {
            LOG.error("Oval validation error in {}:\n{}", error.getCheckName(), error.getMessage());
            String field;

            if (error.getCheckName().contains("com")) {
                if (object instanceof EmployeeData) {
                    field = "email";
                } else {
                    field = "name";
                }
            } else {
                field = error.getCheckName();
            }

            errors.rejectValue(field,"default", error.getMessage());
        }

    }

}
