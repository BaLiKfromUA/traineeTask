package com.aimprosoft.sandbox.controller;

import com.aimprosoft.sandbox.database.department.Department;
import com.aimprosoft.sandbox.database.department.DepartmentDAO;
import com.aimprosoft.sandbox.database.employee.Employee;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * @author BaLiK on 25.03.19
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {

    private static Logger LOG = Logger.getLogger(TestServlet.class);

    private static EmployeeDAO employeeDAO = null;
    private static DepartmentDAO departmentDAO=null;

    @Override
    public void init() {
        try {
            System.out.println("open connection");
            employeeDAO = new EmployeeDAO();
            departmentDAO=new DepartmentDAO();

            Department randomDepartment=new Department(1L);
            randomDepartment.setName("KIY "+UUID.randomUUID().toString());

            departmentDAO.createDepartment(randomDepartment);

            Employee randomUser = new Employee(1L);
            randomUser.setLogin("BaLiK " + UUID.randomUUID().toString());
            randomUser.setEmail(UUID.randomUUID().toString()+"@gmail.com");
            randomUser.setRank(777);
            randomUser.setDepartmentID(1L);
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
        //todo:employeeDAO null situation
        request.setAttribute("employees", employeeDAO.getAllEmployees().toArray());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/employees.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        try {
            System.out.println("close connection");
            employeeDAO.closeConnection();
            departmentDAO.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
