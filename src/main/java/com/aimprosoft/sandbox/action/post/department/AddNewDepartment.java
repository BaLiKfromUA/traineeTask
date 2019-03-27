package com.aimprosoft.sandbox.action.post.department;

import com.aimprosoft.sandbox.action.Action;
import com.aimprosoft.sandbox.database.department.Department;
import com.aimprosoft.sandbox.database.department.DepartmentDAO;
import com.aimprosoft.sandbox.database.employee.EmployeeDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 26.03.19
 */
public class AddNewDepartment implements Action {
    private static Logger LOG = Logger.getLogger(AddNewDepartment.class);
    private final static String URL = "?action-get=default&name=%s&flag=%s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) throws IOException {
        String newDepartmentName = request.getParameter("new name");

        if (departmentDAO.checkDepartment(newDepartmentName)) {
            Department newDepartment = new Department(1L);
            newDepartment.setName(newDepartmentName);

            departmentDAO.createDepartment(newDepartment);
            LOG.info("Department " + newDepartmentName + " was added!");
            response.sendRedirect("/");
        } else {
            String flag = "invalid-new-department";
            response.sendRedirect(String.format(URL, newDepartmentName, flag));
        }

    }
}
