package com.aimprosoft.sandbox.controller.action.post.employee;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.controller.data.EmployeeData;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.util.service.DatabaseService;
import com.aimprosoft.sandbox.util.validator.OvalValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 27.03.19
 */
public class AddNewEmployee implements Action {
    private static Logger LOG = LogManager.getLogger(AddNewEmployee.class);
    private final static String URL = "?action-get=employees&department_id=%d";
    private final static String FAIL_URL = "?action-get=employees&department_id=%s&login=%s&email=%s&rank=%s&date=%s&flag=%s&reason=%s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newLogin = request.getParameter("new login");
        String newEmail = request.getParameter("new email");
        String newRank = request.getParameter("new rank");
        String newDate = request.getParameter("new date");
        String departmentId = request.getParameter("department_id");

        EmployeeData data = new EmployeeData(newLogin, newEmail, newRank, newDate, departmentId);

        if (OvalValidator.getInstance().validate(data)) {

            try {

                if (DatabaseService.getInstance().getEmployeeService().checkEmployee(data)) {
                    DatabaseService.getInstance().getEmployeeService().createEmployee(data);
                    LOG.info("Employee {} was added!", newLogin);
                    response.sendRedirect(String.format(URL, Long.parseLong(departmentId)));
                } else {
                    response.sendRedirect(String.format(FAIL_URL, departmentId, newLogin, newEmail, newRank, newDate, "invalid-new-employee", "email"));
                }

            } catch (DatabaseException e) {
                request.setAttribute("dbError", true);
                request.setAttribute("errorMessage", e.getMessage());
                response.sendRedirect(String.format(URL, Long.parseLong(departmentId)));
            }

        } else {
            response.sendRedirect(String.format(FAIL_URL, departmentId, newLogin, newEmail, newRank, newDate, "invalid-new-employee", "invalid"));
        }
    }
}
