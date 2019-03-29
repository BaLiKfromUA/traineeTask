package com.aimprosoft.sandbox.controller.action.get;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.dao.impl.DepartmentDAO;
import com.aimprosoft.sandbox.dao.impl.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 29.03.19
 */
public class ErrorPage implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) throws ServletException, IOException {
        request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
    }
}
