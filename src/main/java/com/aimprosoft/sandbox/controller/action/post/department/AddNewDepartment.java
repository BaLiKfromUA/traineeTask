package com.aimprosoft.sandbox.controller.action.post.department;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.controller.data.DepartmentData;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.service.DepartmentService;
import com.aimprosoft.sandbox.util.validator.OvalValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 26.03.19
 */
@Component
public class AddNewDepartment implements Action {
    private static Logger LOG = LogManager.getLogger(AddNewDepartment.class);
    private final static String URL = "?action-get=default&name=%s&flag=%s";

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private OvalValidator validator;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DepartmentData data = new DepartmentData(request.getParameter("new name"));
        String flag = "invalid-new-department";

        if (validator.validate(data)) {

            try {
                if (departmentService.checkDepartment(data)) {
                    departmentService.createDepartment(data);
                    LOG.info("Department {} was added!", data.getName());
                    response.sendRedirect("/");
                } else {
                    response.sendRedirect(String.format(URL, data.getName(), flag));
                }
            } catch (DatabaseException e) {
                request.setAttribute("dbError", true);
                request.setAttribute("errorMessage", e.getMessage());
                response.sendRedirect("/");
            }

        } else {
            response.sendRedirect(String.format(URL, data.getName(), flag));
        }
    }
}
