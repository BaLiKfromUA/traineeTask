package com.aimprosoft.sandbox.actions.get;

import com.aimprosoft.sandbox.actions.Action;
import com.aimprosoft.sandbox.database.department.DepartmentDAO;
import com.aimprosoft.sandbox.database.employee.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 26.03.19
 */
public class DepartmentsPage implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) throws ServletException, IOException {
        request.setAttribute("departments", departmentDAO.getAllDepartments().toArray());
        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("flag", request.getParameter("flag"));
        request.getRequestDispatcher("/departments.jsp").forward(request, response);
    }
}
