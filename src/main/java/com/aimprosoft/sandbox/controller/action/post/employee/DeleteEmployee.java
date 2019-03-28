package com.aimprosoft.sandbox.controller.action.post.employee;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.dao.impl.DepartmentDAO;
import com.aimprosoft.sandbox.dao.impl.EmployeeDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 26.03.19
 */
public class DeleteEmployee implements Action {
    private static Logger LOG = Logger.getLogger(DeleteEmployee.class);
    private static String URL = "?action-get=employees&department_id=%d";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) throws IOException {
        Long employeeId = Long.parseLong(request.getParameter("id"));
        employeeDAO.deleteEmployeeById(employeeId);
        LOG.info("Employee " + request.getParameter("login") + " was removed!");

        Long departmentId = Long.parseLong(request.getParameter("department_id"));
        response.sendRedirect(String.format(URL, departmentId));
    }
}
