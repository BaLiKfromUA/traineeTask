package com.aimprosoft.sandbox.controller.action.get;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.controller.data.EmployeeData;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.service.EmployeeService;
import com.aimprosoft.sandbox.util.validator.OvalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 26.03.19
 */
@Component
public class EmployeesPage implements Action {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OvalValidator validator;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("department_id");

        if (validator.validateId(id)) {
            try {
                request.setAttribute("employees", employeeService.getAllByDepartmentId(id).toArray());
            } catch (DatabaseException e) {
                request.setAttribute("dbError", true);
                request.setAttribute("errorMessage", e.getMessage());
            }

            final String login = request.getParameter("login");
            final String email = request.getParameter("email");
            final String rank = request.getParameter("rank");
            final String date = request.getParameter("date");

            if ("invalid".equals(request.getParameter("reason"))) {
                request.setAttribute("errorMessages", validator.getErrors(new EmployeeData(login, email, rank, date, id)).toArray());
            }

            request.setAttribute("login", login);
            request.setAttribute("email", email);
            request.setAttribute("rank", rank);
            request.setAttribute("date", date);
            request.setAttribute("flag", request.getParameter("flag"));
            request.setAttribute("reason", request.getParameter("reason"));

            request.getRequestDispatcher("/employees.jsp").forward(request, response);
        } else {
            System.out.println(id);
            request.setAttribute("reason", "invalid");
            request.setAttribute("errorMessages", new String[]{("Invalid department id!")});
            request.getRequestDispatcher("/employees.jsp").forward(request, response);
        }
    }
}
