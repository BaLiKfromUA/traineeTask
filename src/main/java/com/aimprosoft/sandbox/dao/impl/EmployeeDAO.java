package com.aimprosoft.sandbox.dao.impl;

import com.aimprosoft.sandbox.dao.EmployeeRepo;
import com.aimprosoft.sandbox.util.database.DatabaseManager;
import com.aimprosoft.sandbox.domain.Employee;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author BaLiK on 25.03.19
 */
public class EmployeeDAO implements EmployeeRepo {
    private static Logger LOG = Logger.getLogger(EmployeeDAO.class);
    /**
     * Resources
     **/
    private Connection connection;
    private PreparedStatement createStatement;
    private PreparedStatement checkStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement updateStatement;

    /**
     * Statements
     **/
    private static final String GET_ALL_BY_DEPARTMENT_ID = "SELECT * FROM employees WHERE department_id=? ORDER BY employees.id";
    private static final String CREATE_EMPLOYEE = "INSERT INTO employees (login, email, rank, registration_date, department_id)" +
            " VALUES (?,?,?,?,?)";
    private static final String GET_USER_BY_LOGIN_OR_EMAIL = "SELECT * FROM employees WHERE (employees.email=? AND employees.id!=?)";
    private static final String DELETE_BY_ID = "DELETE FROM employees WHERE employees.id=?";
    private static final String UPDATE_BY_ID = "UPDATE employees " +
            "SET employees.login=?, employees.email=?, employees.rank=?, employees.registration_date=?, employees.department_id=? WHERE employees.id=?";

    public EmployeeDAO() throws IOException, SQLException {
        connection = DatabaseManager.getInstance().getConnection();
        createStatement = connection.prepareStatement(CREATE_EMPLOYEE);
        checkStatement = connection.prepareStatement(GET_USER_BY_LOGIN_OR_EMAIL);
        getAllStatement = connection.prepareStatement(GET_ALL_BY_DEPARTMENT_ID);
        deleteStatement = connection.prepareStatement(DELETE_BY_ID);
        updateStatement = connection.prepareStatement(UPDATE_BY_ID);
    }

    private Employee extractEmployee(ResultSet rs) {
        Employee employee = null;

        try {
            employee = new Employee(Long.parseLong(rs.getString("id")));
            employee.setLogin(rs.getString("login"));
            employee.setEmail(rs.getString("email"));
            employee.setRank(Integer.parseInt(rs.getString("rank")));
            employee.setRegistrationDate(Timestamp.valueOf(rs.getString("registration_date")));
            employee.setDepartmentID(Long.parseLong(rs.getString("department_id")));
        } catch (SQLException e) {
            LOG.error("Can not extract employee\n" + e.getMessage());
        }

        return employee;
    }

    @Override
    public ArrayList<Employee> getAllByDepartmentId(Long id) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            ResultSet rs;
            getAllStatement.setLong(1, id);
            rs = getAllStatement.executeQuery();
            while (rs.next()) {
                employees.add(extractEmployee(rs));
            }
        } catch (SQLException e) {
            LOG.error("Can not get Employees by Department ID\n" + e.getMessage());
        }

        return employees;
    }

    private int setEmployeeData(Employee employee, PreparedStatement statement) throws SQLException {
        int k = 1;
        statement.setString(k++, employee.getLogin());
        statement.setString(k++, employee.getEmail());
        statement.setInt(k++, employee.getRank());
        statement.setTimestamp(k++, new java.sql.Timestamp(employee.getRegistrationDate().getTime()));
        statement.setLong(k++, employee.getDepartmentID());

        return k;
    }

    @Override
    public boolean checkEmployee(Employee employee) {
        try {
            ResultSet rs;
            checkStatement.setString(1, employee.getEmail());
            checkStatement.setLong(2, employee.getID());
            rs = checkStatement.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            LOG.error("Can not get Employees by Email or Login\n" + e.getMessage());
        }
        return true;
    }

    @Override
    public void createEmployee(Employee employee) {
        try {
            setEmployeeData(employee, createStatement);
            int rs = createStatement.executeUpdate();
            LOG.info("Employee create result: " + rs);
        } catch (SQLException e) {
            LOG.error("Can not create Employee\n" + e.getMessage());
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        try {
            deleteStatement.setLong(1, id);
            int rs = deleteStatement.executeUpdate();
            LOG.info("Employee delete result: " + rs);
        } catch (SQLException e) {
            LOG.error("Can not delete Employee " + id + "\n" + e.getMessage());
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        try {
            int k = setEmployeeData(employee, updateStatement);
            updateStatement.setLong(k, employee.getID());
            int rs = updateStatement.executeUpdate();
            LOG.info("Employee delete result: " + rs);
        } catch (SQLException e) {
            LOG.error("Can not delete Employee\n" + e.getMessage());
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
        createStatement.close();
        checkStatement.close();
        getAllStatement.close();
        deleteStatement.close();
        updateStatement.close();
    }
}
