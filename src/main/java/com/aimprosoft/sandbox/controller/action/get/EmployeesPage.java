package com.aimprosoft.sandbox.controller.action.get;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.util.service.DatabaseService;
import com.aimprosoft.sandbox.util.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author BaLiK on 26.03.19
 */
public class EmployeesPage implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("department_id");
        if (Validator.validateId(id)) {
            request.setAttribute("employees", DatabaseService.getInstance().getEmployeeService().getAllByDepartmentId(id).toArray());

            request.setAttribute("login", request.getParameter("login"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("rank", request.getParameter("rank"));
            request.setAttribute("date", request.getParameter("date"));
            request.setAttribute("flag", request.getParameter("flag"));

            request.getRequestDispatcher("/employees.jsp").forward(request, response);
        } else {
            response.sendRedirect("?action-get=error");
        }
    }
}
