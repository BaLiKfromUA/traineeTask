package com.aimprosoft.sandbox.controller;

import com.aimprosoft.sandbox.database.department.Department;
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
//todo:messages if errors!!!
//todo:new logger version
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
        final String action = request.getParameter("action-get");
        if (action != null && action.equals("goto")) {
            Long departmentId = Long.parseLong(request.getParameter("department_id"));
            request.setAttribute("employees", employeeDAO.getAllByDepartmentId(departmentId).toArray());
            dispatcher = request.getRequestDispatcher("/employees.jsp");
        } else {
            request.setAttribute("departments", departmentDAO.getAllDepartments().toArray());
            dispatcher = request.getRequestDispatcher("/departments.jsp");
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String action = request.getParameter("action-post");
        RequestDispatcher dispatcher;

        if (action != null && action.equals("employee delete")) {
            Long employeeId = Long.parseLong(request.getParameter("id"));
            employeeDAO.deleteEmployeeById(employeeId);
            LOG.info("Employee" + request.getParameter("login") + "was removed!");

            Long departmentId = Long.parseLong(request.getParameter("department_id"));
            request.setAttribute("employees", employeeDAO.getAllByDepartmentId(departmentId).toArray());
            dispatcher = request.getRequestDispatcher("/employees.jsp");

        } else if (action != null && action.equals("add new department")) {
            String newDepartmentName = request.getParameter("new name");

            if (departmentDAO.checkDepartment(newDepartmentName)) {
                Department newDepartment = new Department(1L);
                newDepartment.setName(newDepartmentName);

                departmentDAO.createDepartment(newDepartment);
                LOG.info("Department " + newDepartmentName + "was added!");

                request.setAttribute("departments", departmentDAO.getAllDepartments().toArray());
                dispatcher = request.getRequestDispatcher("/departments.jsp");
            } else {
                //todo:error
                request.setAttribute("departments", departmentDAO.getAllDepartments().toArray());
                dispatcher = request.getRequestDispatcher("/departments.jsp");
            }

        } else {
            dispatcher = request.getRequestDispatcher("/departments.jsp");
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
