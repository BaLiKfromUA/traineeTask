package com.aimprosoft.sandbox.controller.action.post.department;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.domain.Department;
import com.aimprosoft.sandbox.dao.impl.DepartmentDAO;
import com.aimprosoft.sandbox.dao.impl.EmployeeDAO;
import com.aimprosoft.sandbox.util.service.DatabaseService;
import com.aimprosoft.sandbox.util.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author BaLiK on 26.03.19
 */
public class AddNewDepartment implements Action {
    private static Logger LOG = Logger.getLogger(AddNewDepartment.class);
    private final static String URL = "?action-get=default&name=%s&flag=%s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        DepartmentData data = new DepartmentData(request.getParameter("new name"));

        if (Validator.validateDepartment(data)) {
            if (DatabaseService.getInstance().getDepartmentService().checkDepartment(data)) {
                DatabaseService.getInstance().getDepartmentService().createDepartment(data);
                LOG.info("Department " + data.getName() + " was added!");
                response.sendRedirect("/");
            } else {
                String flag = "invalid-new-department";
                response.sendRedirect(String.format(URL, data.getName(), flag));
            }

        } else {
            response.sendRedirect("?action-get=error");
        }
    }
}
