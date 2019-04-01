package com.aimprosoft.sandbox.controller.action.post.employee;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.controller.data.EmployeeData;
import com.aimprosoft.sandbox.util.service.DatabaseService;
import com.aimprosoft.sandbox.util.validator.OvalValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author BaLiK on 27.03.19
 */
public class AddNewEmployee implements Action {
    private static Logger LOG = Logger.getLogger(AddNewEmployee.class);
    private final static String URL = "?action-get=employees&department_id=%d";
    private final static String FAIL_URL = "?action-get=employees&department_id=%d&login=%s&email=%s&rank=%d&date=%s&flag=%s";

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
                    LOG.info("Employee " + newLogin + " was added!");
                    response.sendRedirect(String.format(URL, Long.parseLong(departmentId)));
                } else {
                    response.sendRedirect(String.format(FAIL_URL, Long.parseLong(departmentId), newLogin, newEmail, Long.parseLong(newRank), newDate, "invalid-new-employee"));
                }
            } catch (SQLException e) {
                request.setAttribute("dbError", true);
                request.setAttribute("errorMessage", "Fail to add new employee!");
                response.sendRedirect(String.format(URL, Long.parseLong(departmentId)));
            }

        } else {
            response.sendRedirect("?action-get=error");
        }
    }
}
