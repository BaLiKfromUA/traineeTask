package com.aimprosoft.sandbox.controller.action.post.employee;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.service.EmployeeService;
import com.aimprosoft.sandbox.util.validator.OvalValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 26.03.19
 */
@Component
public class DeleteEmployee implements Action {
    private static Logger LOG = LogManager.getLogger(DeleteEmployee.class);
    private static String URL = "?action-get=employees&department_id=%d";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OvalValidator validator;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String depIdStr = request.getParameter("department_id");

        if (validator.validateId(id) && validator.validateId(depIdStr)) {

            try {
                employeeService.deleteEmployeeById(id);
            } catch (DatabaseException e) {
                request.setAttribute("dbError", true);
                request.setAttribute("errorMessage", e.getMessage());
            }

            LOG.info("Employee {} was removed!", id);

            Long departmentId = Long.parseLong(depIdStr);
            response.sendRedirect(String.format(URL, departmentId));
        } else {
            response.sendRedirect("?action-get=error");
        }
    }
}
