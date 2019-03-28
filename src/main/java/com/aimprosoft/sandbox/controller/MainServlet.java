package com.aimprosoft.sandbox.controller;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.controller.action.ActionManager;
import com.aimprosoft.sandbox.dao.impl.DepartmentDAO;
import com.aimprosoft.sandbox.dao.impl.EmployeeDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author BaLiK on 26.03.19
 */
@WebServlet("/")
//todo:messages if errors!!!
//todo:new logger version
public class MainServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(MainServlet.class);

    private static DepartmentDAO departmentDAO = null;
    private static EmployeeDAO employeeDAO = null;
    private static ActionManager actionManager = null;

    //todo:services
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
        final String action = Optional.ofNullable(request.getParameter("action-get")).orElse("default");
        final Action actionToDo = Optional.ofNullable(actionManager.getAction(action)).orElse(actionManager.getAction("default"));
        actionToDo.execute(request, response, employeeDAO, departmentDAO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        final String action = Optional.ofNullable(request.getParameter("action-post")).orElse("default");
        final Action actionToDo = Optional.ofNullable(actionManager.getAction(action)).orElse(actionManager.getAction("default"));
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
