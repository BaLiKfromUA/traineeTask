package com.aimprosoft.sandbox.controller;

import com.aimprosoft.sandbox.database.department.DepartmentDAO;
import com.aimprosoft.sandbox.database.employee.EmployeeDAO;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author BaLiK on 26.03.19
 */
@WebServlet("/")
public class MainServlet extends HttpServlet {
    private static Logger LOG = Logger.getLogger(MainServlet.class);

    private static DepartmentDAO departmentDAO = null;
    private static EmployeeDAO employeeDAO = null;

    @Override
    public void init() {
        LOG.info("Main servlet init...");
        try {
            departmentDAO = new DepartmentDAO();
            employeeDAO = new EmployeeDAO();
        } catch (IOException | SQLException e) {
            LOG.error("Fail to create DAO(s) in Main Servlet!\n" + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher;
        //todo:departmentDAO null situation
        if (request.getParameter("department_id") == null) {
            request.setAttribute("departments", departmentDAO.getAllDepartments().toArray());
            dispatcher = request.getRequestDispatcher("/departments.jsp");
        } else {
            Long departmentId = Long.parseLong(request.getParameter("department_id"));
            request.setAttribute("employees",employeeDAO.getAllByDepartmentId(departmentId).toArray());
            dispatcher = request.getRequestDispatcher("/employees.jsp");
        }
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        LOG.info("Main servlet destroy...");
        try {
            departmentDAO.closeConnection();
            employeeDAO.closeConnection();
        } catch (SQLException e) {
            LOG.error("Fail to close connection(s) in Main Servlet!\n" + e.getMessage());
        }
    }


}
