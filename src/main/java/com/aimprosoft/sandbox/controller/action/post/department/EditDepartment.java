package com.aimprosoft.sandbox.controller.action.post.department;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.domain.Department;
import com.aimprosoft.sandbox.dao.impl.DepartmentDAO;
import com.aimprosoft.sandbox.dao.impl.EmployeeDAO;
import com.aimprosoft.sandbox.util.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 27.03.19
 */
public class EditDepartment implements Action {
    private static Logger LOG = Logger.getLogger(EditDepartment.class);
    private final static String URL = "?action-get=default&name=%s&flag=%s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) throws IOException {
        String idStr = request.getParameter("id");
        String departmentName = request.getParameter("new name");

        DepartmentData data = new DepartmentData(idStr, departmentName);

        if (Validator.validateDepartment(data)) {
            Long id = Long.parseLong(idStr);
            Department department = new Department(id);
            department.setName(departmentName);

            if (departmentDAO.checkDepartment(department)) {
                departmentDAO.updateDepartment(department);
                LOG.info("Department " + departmentName + " was updated!");
                response.sendRedirect("/");
            } else {
                String flag = String.valueOf(id);
                response.sendRedirect(String.format(URL, departmentName, flag));
            }

        } else {
            response.sendRedirect("?action-get=error");
        }

    }
}
