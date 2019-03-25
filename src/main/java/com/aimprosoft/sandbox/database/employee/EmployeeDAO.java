package com.aimprosoft.sandbox.database.employee;

import com.aimprosoft.sandbox.database.DatabaseManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author BaLiK on 25.03.19
 */
public class EmployeeDAO {
    private static Logger LOG = Logger.getLogger(EmployeeDAO.class);
    /**
     * Resources
     **/
    //todo: add prep. statements
    private Connection connection;
    private PreparedStatement createStatement;
    /**
     * Statements
     **/
    private static final String GET_ALL_EMPLOYEES = "SELECT * FROM employees";
    private static final String CREATE_EMPLOYEE = "INSERT INTO employees (login, email, rank, registration_date)" +
            " VALUES (?,?,?,?)";
    private static final String GET_USER_BY_LOGIN_OR_EMAIL = "SELECT * FROM employees WHERE (login = ? OR email=?)";

    public EmployeeDAO() throws IOException, SQLException {
        connection = DatabaseManager.getInstance().getConnection();
        createStatement = connection.prepareStatement(CREATE_EMPLOYEE);
    }

    private Employee extractEmployee(ResultSet rs) {
        Employee employee = null;

        try {
            employee = new Employee(Long.parseLong(rs.getString("id")));
            employee.setLogin(rs.getString("login"));
            employee.setEmail(rs.getString("email"));
            employee.setRank(Integer.parseInt(rs.getString("rank")));
            employee.setRegistrationDate(Timestamp.valueOf(rs.getString("registration_date")));
        } catch (SQLException e) {
            LOG.error("Can not extract employee\n" + e.getMessage());
        }

        return employee;
    }

    public ArrayList<Employee> getAllEmployees() {
        Statement statement;
        ResultSet rs;
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            statement = connection.createStatement();

            rs = statement.executeQuery(GET_ALL_EMPLOYEES);

            while (rs.next()) {
                employees.add(extractEmployee(rs));
            }

        } catch (SQLException e) {
            LOG.error("Can not get Employees\n" + e.getMessage());
        }

        return employees;
    }

    private int setEmployeeData(Employee employee, PreparedStatement statement) throws SQLException {
        int k = 1;
        statement.setString(k++, employee.getLogin());
        statement.setString(k++, employee.getEmail());
        statement.setInt(k++, employee.getRank());
        statement.setTimestamp(k++, new java.sql.Timestamp(employee.getRegistrationDate().getTime()));

        return k;
    }

    public void createEmployee(Employee employee) {
        try {
            setEmployeeData(employee, createStatement);
            int rs = createStatement.executeUpdate();
            LOG.info("User create result: " + rs);
        } catch (SQLException e) {
            LOG.error("Can not create User\n" + e.getMessage());
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
        createStatement.close();
    }
}
