package com.aimprosoft.sandbox.controller.action.post.employee;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.util.service.DatabaseService;
import com.aimprosoft.sandbox.util.validator.Validator;
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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String depIdStr = request.getParameter("department_id");

        if (Validator.validateId(id) && Validator.validateId(depIdStr)) {
            DatabaseService.getInstance().getEmployeeService().deleteEmployeeById(id);
            LOG.info("Employee " + id + " was removed!");
            Long departmentId = Long.parseLong(depIdStr);
            response.sendRedirect(String.format(URL, departmentId));
        } else {
            response.sendRedirect("?action-get=error");
        }
    }
}
