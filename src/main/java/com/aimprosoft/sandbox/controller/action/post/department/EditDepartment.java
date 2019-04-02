package com.aimprosoft.sandbox.controller.action.post.department;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.util.service.DatabaseService;
import com.aimprosoft.sandbox.util.validator.OvalValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author BaLiK on 27.03.19
 */
public class EditDepartment implements Action {
    private static Logger LOG = LogManager.getLogger(EditDepartment.class);
    private final static String URL = "?action-get=default&name=%s&flag=%s";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = Optional.ofNullable(request.getParameter("id")).orElse("1");
        String departmentName = Optional.ofNullable(request.getParameter("new name")).orElse("Department");

        DepartmentData data = new DepartmentData(idStr, departmentName);

        if (OvalValidator.getInstance().validate(data)) {

            try {
                if (DatabaseService.getInstance().getDepartmentService().checkDepartment(data)) {
                    DatabaseService.getInstance().getDepartmentService().updateDepartment(data);
                    LOG.info("Department {} was updated!", idStr);
                    response.sendRedirect("/");
                } else {
                    response.sendRedirect(String.format(URL, departmentName, idStr));
                }
            } catch (DatabaseException e) {
                request.setAttribute("dbError", true);
                request.setAttribute("errorMessage", e.getMessage());
                response.sendRedirect("/");
            }

        } else {
            response.sendRedirect(String.format(URL, departmentName, idStr));
        }

    }
}
