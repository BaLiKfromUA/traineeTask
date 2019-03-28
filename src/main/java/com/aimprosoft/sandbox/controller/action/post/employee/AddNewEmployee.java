package com.aimprosoft.sandbox.controller.action.post.employee;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.dao.impl.DepartmentDAO;
import com.aimprosoft.sandbox.domain.Employee;
import com.aimprosoft.sandbox.dao.impl.EmployeeDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 27.03.19
 */
public class AddNewEmployee implements Action {
    private static Logger LOG = Logger.getLogger(AddNewEmployee.class);
    private final static String URL = "?action-get=employees&department_id=%d";
    private final static String FAIL_URL = "?action-get=employees&department_id=%d&login=%s&email=%s&rank=%d&date=%s&flag=%s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) throws IOException {
        String newLogin = request.getParameter("new login");
        String newEmail = request.getParameter("new email");
        Integer newRank = Integer.parseInt(request.getParameter("new rank"));
        String newDate = request.getParameter("new date");
        Long departmentId = Long.parseLong(request.getParameter("department_id"));

        Employee employee = new Employee(0L);
        employee.setLogin(newLogin);
        employee.setEmail(newEmail);
        employee.setRank(newRank);
        employee.setRegistrationDate(newDate);
        employee.setDepartmentID(departmentId);

        //todo:validate
        if (employeeDAO.checkEmployee(employee)) {
            employeeDAO.createEmployee(employee);
            LOG.info("Employee " + newLogin + " was added!");
            response.sendRedirect(String.format(URL, departmentId));
        } else {
            response.sendRedirect(String.format(FAIL_URL, departmentId, newLogin, newEmail, newRank, newDate, "invalid-new-employee"));
        }
    }
}
