package com.aimprosoft.sandbox.controller.action;

import com.aimprosoft.sandbox.dao.impl.DepartmentDAO;
import com.aimprosoft.sandbox.dao.impl.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 26.03.19
 */
public interface Action {
    void execute(HttpServletRequest request, HttpServletResponse response, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) throws ServletException, IOException;
}
