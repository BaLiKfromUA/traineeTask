package com.aimprosoft.sandbox.controller;

import com.aimprosoft.sandbox.actions.Action;
import com.aimprosoft.sandbox.actions.ActionManager;
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
    private static ActionManager actionManager = null;

    @Override
    public void init() {
        LOG.info("Main servlet init...");
        actionManager = new ActionManager();
        try {
            departmentDAO = new DepartmentDAO();
            employeeDAO = new EmployeeDAO();
        } catch (IOException | SQLException e) {
            LOG.error("Fail to create DAO(s) in Main Servlet!\n" + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action-get");
        if (action == null) {
            action = "default";
        }
        Action actionToDo = actionManager.getAction(action);
        actionToDo.execute(request, response, employeeDAO, departmentDAO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        final String action = request.getParameter("action-post");
        Action actionToDo = actionManager.getAction(action);
        actionToDo.execute(request, response, employeeDAO, departmentDAO);
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
