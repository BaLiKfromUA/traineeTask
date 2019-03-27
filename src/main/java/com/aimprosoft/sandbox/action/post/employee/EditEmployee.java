package com.aimprosoft.sandbox.action.post.employee;

import com.aimprosoft.sandbox.action.Action;
import com.aimprosoft.sandbox.database.department.DepartmentDAO;
import com.aimprosoft.sandbox.database.employee.Employee;
import com.aimprosoft.sandbox.database.employee.EmployeeDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
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
    public void execute(HttpServletRequest request, HttpServletResponse response, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("id"));
        String newLogin = request.getParameter("new login");
        String newEmail = request.getParameter("new email");
        Integer newRank = Integer.parseInt(request.getParameter("new rank"));
        String newDate = request.getParameter("new date");
        Long departmentId = Long.parseLong(request.getParameter("department_id"));


        Employee employee = new Employee(userId);
        employee.setLogin(newLogin);
        employee.setEmail(newEmail);
        employee.setRank(newRank);
        employee.setRegistrationDate(newDate);
        employee.setDepartmentID(departmentId);

        if (employeeDAO.checkEmployee(employee)) {
            employeeDAO.updateEmployee(employee);
            LOG.info("Employee " + newLogin + " was updated!");
            response.sendRedirect(String.format(URL, departmentId));
        } else {
            response.sendRedirect(String.format(FAIL_URL, departmentId, newLogin, newEmail, newRank, newDate, String.valueOf(userId)));
        }

    }
}
