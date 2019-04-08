package com.aimprosoft.sandbox.util.validator.annotation.department;

import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.service.DepartmentService;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author BaLiK on 05.04.19
 */
public class UniqueDepartmentCheck extends AbstractAnnotationCheck<UniqueDepartment> {
    @Autowired
    private DepartmentService departmentService;

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext oValContext, Validator validator) throws OValException {
        if (validatedObject == null) {
            return false;
        }
        DepartmentData data = (DepartmentData) validatedObject;
        try {
            return departmentService.checkDepartment(data);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
