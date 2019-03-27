package com.aimprosoft.sandbox.actions.post.department;

import com.aimprosoft.sandbox.actions.Action;
import com.aimprosoft.sandbox.database.department.DepartmentDAO;
import com.aimprosoft.sandbox.database.employee.EmployeeDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 26.03.19
 */
public class DeleteDepartment implements Action {
    private static Logger LOG = Logger.getLogger(DeleteDepartment.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) throws IOException {
        Long departmentId = Long.parseLong(request.getParameter("id"));
        departmentDAO.deleteDepartmentById(departmentId);
        LOG.info("Department " + request.getParameter("name") + " was removed!");
        response.sendRedirect("/");
    }
}
