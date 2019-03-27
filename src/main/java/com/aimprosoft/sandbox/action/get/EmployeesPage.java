package com.aimprosoft.sandbox.action.get;

import com.aimprosoft.sandbox.action.Action;
import com.aimprosoft.sandbox.database.department.DepartmentDAO;
import com.aimprosoft.sandbox.database.employee.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 26.03.19
 */
public class EmployeesPage implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) throws ServletException, IOException {
        Long departmentId = Long.parseLong(request.getParameter("department_id"));
        request.setAttribute("employees", employeeDAO.getAllByDepartmentId(departmentId).toArray());

        request.setAttribute("login", request.getParameter("login"));
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("rank", request.getParameter("rank"));
        request.setAttribute("date", request.getParameter("date"));
        request.setAttribute("flag", request.getParameter("flag"));

        request.getRequestDispatcher("/employees.jsp").forward(request, response);
    }
}
