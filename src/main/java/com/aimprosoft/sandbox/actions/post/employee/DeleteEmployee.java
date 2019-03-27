package com.aimprosoft.sandbox.actions.post.employee;

import com.aimprosoft.sandbox.actions.Action;
import com.aimprosoft.sandbox.database.department.DepartmentDAO;
import com.aimprosoft.sandbox.database.employee.EmployeeDAO;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author BaLiK on 26.03.19
 */
public class DeleteEmployee implements Action {
    private static Logger LOG = Logger.getLogger(DeleteEmployee.class);

    @Override
    public RequestDispatcher execute(HttpServletRequest request, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) {
        Long employeeId = Long.parseLong(request.getParameter("id"));
        employeeDAO.deleteEmployeeById(employeeId);
        LOG.info("Employee " + request.getParameter("login") + " was removed!");

        Long departmentId = Long.parseLong(request.getParameter("department_id"));
        request.setAttribute("employees", employeeDAO.getAllByDepartmentId(departmentId).toArray());
        return request.getRequestDispatcher("/employees.jsp");
    }
}
