package com.aimprosoft.sandbox.controller;

import com.aimprosoft.sandbox.actions.Action;
import com.aimprosoft.sandbox.actions.get.DepartmentsPage;
import com.aimprosoft.sandbox.actions.get.EmployeesPage;
import com.aimprosoft.sandbox.actions.post.AddNewDepartment;
import com.aimprosoft.sandbox.actions.post.DeleteDepartment;
import com.aimprosoft.sandbox.actions.post.DeleteEmployee;
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
        final String action = request.getParameter("action-get");
        Action actionToDo;
        if (action != null && action.equals("goto")) {
            actionToDo = new EmployeesPage();
        } else {
            actionToDo = new DepartmentsPage();
        }

        RequestDispatcher dispatcher = actionToDo.execute(request, employeeDAO, departmentDAO);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String action = request.getParameter("action-post");
        RequestDispatcher dispatcher;
        Action actionToDo = null;
        if (action != null && action.equals("employee delete")) {
            actionToDo = new DeleteEmployee();
        } else if (action != null && action.equals("add new department")) {
            actionToDo = new AddNewDepartment();
        }
        else if(action!=null && action.equals("department delete")){
            actionToDo=new DeleteDepartment();
        }
        else {
            dispatcher = request.getRequestDispatcher("/departments.jsp");
            dispatcher.forward(request, response);
        }

        if (actionToDo != null)//todo:remove
        {
            dispatcher = actionToDo.execute(request, employeeDAO, departmentDAO);
            dispatcher.forward(request, response);
        }
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
