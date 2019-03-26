package com.aimprosoft.sandbox.actions.get;

import com.aimprosoft.sandbox.actions.Action;
import com.aimprosoft.sandbox.database.department.DepartmentDAO;
import com.aimprosoft.sandbox.database.employee.EmployeeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author BaLiK on 26.03.19
 */
public class EmployeesPage implements Action {
    @Override
    public RequestDispatcher execute(HttpServletRequest request, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) {
        Long departmentId = Long.parseLong(request.getParameter("department_id"));
        request.setAttribute("employees", employeeDAO.getAllByDepartmentId(departmentId).toArray());
        return request.getRequestDispatcher("/employees.jsp");
    }
}
