package com.aimprosoft.sandbox.actions.post.department;

import com.aimprosoft.sandbox.actions.Action;
import com.aimprosoft.sandbox.database.department.Department;
import com.aimprosoft.sandbox.database.department.DepartmentDAO;
import com.aimprosoft.sandbox.database.employee.EmployeeDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 27.03.19
 */
public class EditDepartment implements Action {
    private static Logger LOG = Logger.getLogger(EditDepartment.class);
    private final static String URL = "?action-get=default&name=%s&flag=%s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String departmentName = request.getParameter("name");

        if (departmentDAO.checkDepartment(departmentName)) {
            Department department = new Department(id);
            department.setName(departmentName);
            departmentDAO.updateDepartment(department);

            LOG.info("Department " + departmentName + " was updated!");
            response.sendRedirect("/");
        } else {
            String flag = String.valueOf(id);
            response.sendRedirect(String.format(URL, departmentName, flag));
        }


    }
}
