package com.aimprosoft.sandbox.controller.action.get;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.util.service.DatabaseService;
import com.aimprosoft.sandbox.util.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 26.03.19
 */
//todo: validate backend for employee
public class EmployeesPage implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("department_id");

        if (Validator.validateId(id)) {
            try {
                request.setAttribute("employees", DatabaseService.getInstance().getEmployeeService().getAllByDepartmentId(id).toArray());
            } catch (DatabaseException e) {
                request.setAttribute("dbError", true);
                request.setAttribute("errorMessage", e.getMessage());
            }


            request.setAttribute("login", request.getParameter("login"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("rank", request.getParameter("rank"));
            request.setAttribute("date", request.getParameter("date"));
            request.setAttribute("flag", request.getParameter("flag"));

            request.getRequestDispatcher("/employees.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessages", new String[]{("Invalid department id!")});
            request.getRequestDispatcher("/employees.jsp").forward(request, response);
        }
    }
}
