package com.aimprosoft.sandbox.controller.action.post.department;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.dao.impl.DepartmentDAO;
import com.aimprosoft.sandbox.dao.impl.EmployeeDAO;
import com.aimprosoft.sandbox.util.validator.Validator;
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
        String id = request.getParameter("id");
        if (Validator.validateId(id)) {
            Long departmentId = Long.parseLong(id);
            departmentDAO.deleteDepartmentById(departmentId);
            LOG.info("Department " + request.getParameter("name") + " was removed!");
            response.sendRedirect("/");
        } else {
            response.sendRedirect("?action-get=error");
        }
    }
}
