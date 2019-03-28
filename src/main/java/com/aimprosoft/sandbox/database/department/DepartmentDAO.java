package com.aimprosoft.sandbox.database.department;

import com.aimprosoft.sandbox.database.DatabaseManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author BaLiK on 25.03.19
 */
public class DepartmentDAO {
    private static Logger LOG = Logger.getLogger(DepartmentDAO.class);
    /**
     * Resources
     **/
    private Connection connection;
    private PreparedStatement createStatement;
    private PreparedStatement checkStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement updateStatement;

    /**
     * Statements
     **/
    private static final String GET_ALL_DEPARTMENTS = "SELECT * FROM departments ORDER BY departments.id";
    private static final String CHECK_DEPARTMENT = "SELECT * FROM departments WHERE name = ?";
    private static final String CREATE_DEPARTMENT = "INSERT INTO departments (name)" +
            " VALUES (?)";
    private static final String DELETE_BY_ID = "DELETE FROM departments WHERE departments.id=?";
    private static final String UPDATE_BY_ID = "UPDATE departments SET departments.name=? WHERE departments.id=?";

    public DepartmentDAO() throws IOException, SQLException {
        connection = DatabaseManager.getInstance().getConnection();
        createStatement = connection.prepareStatement(CREATE_DEPARTMENT);
        checkStatement = connection.prepareStatement(CHECK_DEPARTMENT);
        deleteStatement = connection.prepareStatement(DELETE_BY_ID);
        updateStatement = connection.prepareStatement(UPDATE_BY_ID);
    }

    public void deleteDepartmentById(Long id) {
        try {
            deleteStatement.setLong(1, id);
            int rs = deleteStatement.executeUpdate();
            LOG.info("Department delete result: " + rs);
        } catch (SQLException e) {
            LOG.error("Can not delete Department " + id + "\n" + e.getMessage());
        }
    }

    public void createDepartment(Department department) {
        try {
            createStatement.setString(1, department.getName());
            int rs = createStatement.executeUpdate();
            LOG.info("Department create result: " + rs);
        } catch (SQLException e) {
            LOG.error("Can not create Department\n" + e.getMessage());
        }
    }

    public boolean checkDepartment(String department) {
        try {
            ResultSet rs;
            checkStatement.setString(1, department);
            rs = checkStatement.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            LOG.error("Can not get Department by Name\n" + e.getMessage());
        }
        return true;
    }

    private Department extractDepartment(ResultSet rs) {
        Department department = null;

        try {
            department = new Department(Long.parseLong(rs.getString("id")));
            department.setName(rs.getString("name"));
        } catch (SQLException e) {
            LOG.error("Can not extract department\n" + e.getMessage());
        }

        return department;
    }

    public ArrayList<Department> getAllDepartments() {
        Statement statement;
        ResultSet rs;
        ArrayList<Department> departments = new ArrayList<>();

        try {
            statement = connection.createStatement();

            rs = statement.executeQuery(GET_ALL_DEPARTMENTS);

            while (rs.next()) {
                departments.add(extractDepartment(rs));
            }

        } catch (SQLException e) {
            LOG.error("Can not get Departments\n" + e.getMessage());
        }

        return departments;
    }

    public void updateDepartment(Department department) {
        try {
            updateStatement.setString(1, department.getName());
            updateStatement.setLong(2, department.getID());
            int rs = updateStatement.executeUpdate();
            LOG.info("Department update result: " + rs);
        } catch (SQLException e) {
            LOG.error("Can not update department\n" + e.getMessage());
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
        createStatement.close();
        checkStatement.close();
        deleteStatement.close();
        updateStatement.close();
    }
}
