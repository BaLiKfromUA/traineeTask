package com.aimprosoft.sandbox.controller.action.post.employee;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.controller.data.EmployeeData;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.util.service.DatabaseService;
import com.aimprosoft.sandbox.util.validator.OvalValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 27.03.19
 */
public class EditEmployee implements Action {
    private static Logger LOG = Logger.getLogger(EditEmployee.class);
    private final static String URL = "?action-get=employees&department_id=%d";
    private final static String FAIL_URL = "?action-get=employees&department_id=%d&login=%s&email=%s&rank=%d&date=%s&flag=%s";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("id");
        String newLogin = request.getParameter("new login");
        String newEmail = request.getParameter("new email");
        String newRank = request.getParameter("new rank");
        String newDate = request.getParameter("new date");
        String departmentId = request.getParameter("department_id");

        EmployeeData data = new EmployeeData(userId, newLogin, newEmail, newRank, newDate, departmentId);

        if (OvalValidator.getInstance().validate(data)) {
            try {
                if (DatabaseService.getInstance().getEmployeeService().checkEmployee(data)) {
                    DatabaseService.getInstance().getEmployeeService().updateEmployee(data);
                    LOG.info("Employee " + userId + " was updated!");
                    response.sendRedirect(String.format(URL, Long.parseLong(departmentId)));
                } else {
                    response.sendRedirect(String.format(FAIL_URL, Long.parseLong(departmentId), newLogin, newEmail, Integer.parseInt(newRank), newDate, userId));
                }
            } catch (DatabaseException e) {
                request.setAttribute("dbError", true);
                request.setAttribute("errorMessage", e.getMessage());
                response.sendRedirect(String.format(URL, Long.parseLong(departmentId)));
            }

        } else {
            response.sendRedirect("?action-get=error");
        }
    }
}
