package com.aimprosoft.sandbox.controller.action.get;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.util.service.DatabaseService;
import com.aimprosoft.sandbox.util.validator.OvalValidator;
import com.aimprosoft.sandbox.util.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author BaLiK on 26.03.19
 */
public class DepartmentsPage implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String invalidId = Optional.ofNullable(request.getParameter("flag")).orElse("1");
        if (!Validator.validateId(invalidId)) {
            invalidId = "1";
        }
        String invalidName = Optional.ofNullable(request.getParameter("name")).orElse("Department");

        request.setAttribute("errorMessages", OvalValidator.getInstance().
                getErrors(new DepartmentData(invalidId, invalidName)).toArray());

        try {
            request.setAttribute("departments", DatabaseService.getInstance().getDepartmentService().getAllDepartments().toArray());
        } catch (SQLException e) {
            request.setAttribute("dbError", true);
            request.setAttribute("errorMessage", "Fail to get departments!");
        }

        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("flag", request.getParameter("flag"));

        request.getRequestDispatcher("/departments.jsp").forward(request, response);
    }
}
