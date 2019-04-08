package com.aimprosoft.sandbox.util.validator.annotation.employee;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author BaLiK on 08.04.19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@net.sf.oval.configuration.annotation.Constraint(checkWith = UniqueEmployeeCheck.class)
public @interface UniqueEmployee {
    String message();
}

