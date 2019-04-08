package com.aimprosoft.sandbox.util.validator.annotation.department;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author BaLiK on 05.04.19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@net.sf.oval.configuration.annotation.Constraint(checkWith = UniqueDepartmentCheck.class)
public @interface UniqueDepartment {
    String message();
}
