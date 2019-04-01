package com.aimprosoft.sandbox.dao.impl;

import com.aimprosoft.sandbox.dao.DepartmentRepo;
import com.aimprosoft.sandbox.util.database.DatabaseManager;
import com.aimprosoft.sandbox.domain.Department;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author BaLiK on 25.03.19
 */
public class DepartmentRepoImpl implements DepartmentRepo {
    private static Logger LOG = Logger.getLogger(DepartmentRepoImpl.class);

    /**
     * Statements
     **/
    private static final String GET_ALL_DEPARTMENTS = "SELECT * FROM departments ORDER BY departments.id";
    private static final String CHECK_DEPARTMENT = "SELECT * FROM departments WHERE (departments.name = ? and departments.id!=?)";
    private static final String CREATE_DEPARTMENT = "INSERT INTO departments (name)" +
            " VALUES (?)";
    private static final String DELETE_BY_ID = "DELETE FROM departments WHERE departments.id=?";
    private static final String UPDATE_BY_ID = "UPDATE departments SET departments.name=? WHERE departments.id=?";

    @Override
    public void deleteDepartmentById(Long id) throws IOException, SQLException {
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(DELETE_BY_ID)) {
            deleteStatement.setLong(1, id);
            int rs = deleteStatement.executeUpdate();
            LOG.info("Department delete result: " + rs);
        } catch (SQLException | IOException e) {
            LOG.error("Can not delete Department " + id + "\n" + e.getMessage());
            throw e;
        }
    }

    @Override
    public void createDepartment(Department department) throws IOException, SQLException {
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement createStatement = connection.prepareStatement(CREATE_DEPARTMENT)) {
            createStatement.setString(1, department.getName());
            int rs = createStatement.executeUpdate();
            LOG.info("Department create result: " + rs);
        } catch (SQLException | IOException e) {
            LOG.error("Can not create Department\n" + e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean checkDepartment(Department department) throws IOException, SQLException {
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(CHECK_DEPARTMENT)) {
            ResultSet rs;
            checkStatement.setString(1, department.getName());
            checkStatement.setLong(2, department.getID());
            rs = checkStatement.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException | IOException e) {
            LOG.error("Can not get Department by Name\n" + e.getMessage());
            throw e;
        }
        return true;
    }

    private Department extractDepartment(ResultSet rs) throws SQLException {
        Department department;

        try {
            department = new Department(Long.parseLong(rs.getString("id")));
            department.setName(rs.getString("name"));
        } catch (SQLException e) {
            LOG.error("Can not extract department\n" + e.getMessage());
            throw e;
        }

        return department;
    }

    @Override
    public ArrayList<Department> getAllDepartments() throws IOException, SQLException {
        Statement statement;
        ResultSet rs;
        ArrayList<Department> departments = new ArrayList<>();

        try (Connection connection = DatabaseManager.getInstance().getConnection()) {
            statement = connection.createStatement();

            rs = statement.executeQuery(GET_ALL_DEPARTMENTS);

            while (rs.next()) {
                departments.add(extractDepartment(rs));
            }

        } catch (SQLException | IOException e) {
            LOG.error("Can not get Departments\n" + e.getMessage());
            throw e;
        }

        return departments;
    }

    @Override
    public void updateDepartment(Department department) throws IOException, SQLException {
        try (Connection connection = DatabaseManager.getInstance().getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            updateStatement.setString(1, department.getName());
            updateStatement.setLong(2, department.getID());
            int rs = updateStatement.executeUpdate();
            LOG.info("Department update result: " + rs);
        } catch (SQLException | IOException e) {
            LOG.error("Can not update department\n" + e.getMessage());
            throw e;
        }
    }

}
