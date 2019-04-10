package com.aimprosoft.sandbox.controller.servlet.action.post.employee;

import com.aimprosoft.sandbox.controller.servlet.action.Action;
import com.aimprosoft.sandbox.controller.data.EmployeeData;
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
 * @author BaLiK on 27.03.19
 */
@Component
public class EditEmployee implements Action {
    private static Logger LOG = LogManager.getLogger(EditEmployee.class);
    private final static String URL = "/old/employees?department_id=%d";
    private final static String FAIL_URL = "/old/employees?department_id=%s&login=%s&email=%s&rank=%s&date=%s&flag=%s&reason=%s";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OvalValidator validator;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("id");
        String newLogin = request.getParameter("new login");
        String newEmail = request.getParameter("new email");
        String newRank = request.getParameter("new rank");
        String newDate = request.getParameter("new date");
        String departmentId = request.getParameter("department_id");

        EmployeeData data = new EmployeeData(userId, newLogin, newEmail, newRank, newDate, departmentId);


        if (validator.validate(data)) {
            try {
                employeeService.updateEmployee(data);
                LOG.info("Employee {} was updated!", userId);
                response.sendRedirect(String.format(URL, Long.parseLong(departmentId)));
            } catch (DatabaseException e) {
                request.setAttribute("dbError", true);
                request.setAttribute("errorMessage", e.getMessage());
                response.sendRedirect(String.format(URL, Long.parseLong(departmentId)));
            }

        } else {
            response.sendRedirect(String.format(FAIL_URL, departmentId, newLogin, newEmail, newRank, newDate, userId, "invalid"));
        }
    }
}
