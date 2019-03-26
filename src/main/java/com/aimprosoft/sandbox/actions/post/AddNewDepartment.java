package com.aimprosoft.sandbox.actions.post;

import com.aimprosoft.sandbox.actions.Action;
import com.aimprosoft.sandbox.database.department.Department;
import com.aimprosoft.sandbox.database.department.DepartmentDAO;
import com.aimprosoft.sandbox.database.employee.EmployeeDAO;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author BaLiK on 26.03.19
 */
public class AddNewDepartment implements Action {
    private static Logger LOG = Logger.getLogger(AddNewDepartment.class);

    @Override
    public RequestDispatcher execute(HttpServletRequest request, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) {
        String newDepartmentName = request.getParameter("new name");

        if (departmentDAO.checkDepartment(newDepartmentName)) {
            Department newDepartment = new Department(1L);
            newDepartment.setName(newDepartmentName);

            departmentDAO.createDepartment(newDepartment);
            LOG.info("Department " + newDepartmentName + " was added!");

            request.setAttribute("departments", departmentDAO.getAllDepartments().toArray());
            return request.getRequestDispatcher("/departments.jsp");
        }

        //todo:error
        request.setAttribute("departments", departmentDAO.getAllDepartments().toArray());
        return request.getRequestDispatcher("/departments.jsp");
    }
}
