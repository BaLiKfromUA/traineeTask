package com.aimprosoft.sandbox.controller;

import com.aimprosoft.sandbox.database.employee.EmployeeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author BaLiK on 25.03.19
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {

    private static EmployeeDAO employeeDAO=null;

    @Override
    public void init() {
        try {
            System.out.println("open connection");
            employeeDAO = new EmployeeDAO();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //todo:employeeDAO null situation
        request.setAttribute("employees", employeeDAO.getAllEmployees().toArray());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        try {
            System.out.println("close connection");
            employeeDAO.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
