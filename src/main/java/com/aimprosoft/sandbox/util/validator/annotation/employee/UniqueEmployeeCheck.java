package com.aimprosoft.sandbox.util.validator.annotation.employee;

import com.aimprosoft.sandbox.controller.data.EmployeeData;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.service.EmployeeService;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author BaLiK on 08.04.19
 */
public class UniqueEmployeeCheck extends AbstractAnnotationCheck<UniqueEmployee> {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) throws OValException {
        if (validatedObject == null) {
            return false;
        }

        EmployeeData data = (EmployeeData) validatedObject;

        try {
            return employeeService.checkEmployee(data);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return false;
    }
}
