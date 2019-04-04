package com.aimprosoft.sandbox.controller.action.post.department;

import com.aimprosoft.sandbox.controller.action.Action;
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
public class DeleteDepartment implements Action {
    private static Logger LOG = LogManager.getLogger(DeleteDepartment.class);

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private OvalValidator validator;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (validator.validateId(id)) {

            try {
                departmentService.deleteDepartmentById(id);
            } catch (DatabaseException e) {
                request.setAttribute("dbError", true);
                request.setAttribute("errorMessage", e.getMessage());
            }

            LOG.info("Department {} was removed!", id);
            response.sendRedirect("/");
        } else {
            response.sendRedirect("?action-get=error");
        }
    }
}
