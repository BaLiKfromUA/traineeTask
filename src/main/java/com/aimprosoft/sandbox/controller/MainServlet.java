package com.aimprosoft.sandbox.controller;

import com.aimprosoft.sandbox.database.department.DepartmentDAO;
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

    private static DepartmentDAO departmentDAO=null;

    @Override
    public void init() {
        LOG.info("Main servlet init...");
        try {
            departmentDAO=new DepartmentDAO();
        } catch (IOException | SQLException e) {
            LOG.error("Fail to create department DAO in Main Servlet!\n"+e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //todo:departmentDAO null situation
        request.setAttribute("departments", departmentDAO.getAllDepartments().toArray());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/departments.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        LOG.info("Main servlet destroy...");
        try {
            departmentDAO.closeConnection();
        } catch (SQLException e) {
            LOG.error("Fail to close connection(s) in Main Servlet!\n"+e.getMessage());
        }
    }


}
