package com.aimprosoft.sandbox.controller.action.get;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.service.DepartmentService;
import com.aimprosoft.sandbox.util.validator.OvalValidator;
import com.aimprosoft.sandbox.util.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author BaLiK on 26.03.19
 */
@Controller
public class DepartmentsPage implements Action {

    @Autowired
    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String invalidId = Optional.ofNullable(request.getParameter("flag")).orElse("0");

        if (!Validator.validateId(invalidId)) {
            invalidId = "0";
        }

        final String invalidName = Optional.ofNullable(request.getParameter("name")).orElse("Department");

        request.setAttribute("errorMessages", OvalValidator.getInstance().
                getErrors(new DepartmentData(invalidId, invalidName)).toArray());

        try {
            request.setAttribute("departments", departmentService.getAllDepartments().toArray());
        } catch (DatabaseException e) {
            request.setAttribute("dbError", true);
            request.setAttribute("errorMessage", e.getMessage());
        }

        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("flag", request.getParameter("flag"));

        request.getRequestDispatcher("/departments.jsp").forward(request, response);
    }
}
