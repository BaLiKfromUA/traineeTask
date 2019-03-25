package com.aimprosoft.sandbox.controller;

import com.aimprosoft.sandbox.database.employee.Employee;
import com.aimprosoft.sandbox.database.employee.EmployeeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * @author BaLiK on 25.03.19
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {

    private static EmployeeDAO employeeDAO;

    @Override
    public void init() {
        try {
            System.out.println("open connection");
            employeeDAO = new EmployeeDAO();

            Employee randomUser = new Employee((long) 1);
            randomUser.setLogin("BaLiK " + UUID.randomUUID().toString());
            randomUser.setEmail(randomUser.getLogin());
            randomUser.setRank(777);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            randomUser.setRegistrationDate(new Date(currentTime.getTime()));

            employeeDAO.createEmployee(randomUser);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(employeeDAO.getAllEmployees().size());
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
