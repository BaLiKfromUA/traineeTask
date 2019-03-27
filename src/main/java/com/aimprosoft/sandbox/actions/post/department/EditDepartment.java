package com.aimprosoft.sandbox.actions.post.department;

import com.aimprosoft.sandbox.actions.Action;
import com.aimprosoft.sandbox.database.department.Department;
import com.aimprosoft.sandbox.database.department.DepartmentDAO;
import com.aimprosoft.sandbox.database.employee.EmployeeDAO;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author BaLiK on 27.03.19
 */
public class EditDepartment implements Action {
    private static Logger LOG = Logger.getLogger(EditDepartment.class);

    @Override
    public RequestDispatcher execute(HttpServletRequest request, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) {
        Long id = Long.parseLong(request.getParameter("id"));
        String departmentName = request.getParameter("name");

        if (departmentDAO.checkDepartment(departmentName)) {
            Department department = new Department(id);
            department.setName(departmentName);
            departmentDAO.updateDepartment(department);

            LOG.info("Department " + departmentName + " was updated!");

            request.setAttribute("departments", departmentDAO.getAllDepartments().toArray());
            request.setAttribute("name", "");

            return request.getRequestDispatcher("/departments.jsp");
        }

        request.setAttribute("flag", String.valueOf(id));
        request.setAttribute("name", departmentName);//fix it
        return request.getRequestDispatcher("/departments.jsp");
    }
}
