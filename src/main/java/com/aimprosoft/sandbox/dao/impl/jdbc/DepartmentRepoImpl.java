package com.aimprosoft.sandbox.dao.impl;

import com.aimprosoft.sandbox.dao.DepartmentRepo;
import com.aimprosoft.sandbox.domain.Department;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.util.database.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author BaLiK on 25.03.19
 */
@Repository
public class DepartmentRepoImpl implements DepartmentRepo {
    private static Logger LOG = LogManager.getLogger(DepartmentRepoImpl.class);

    public DepartmentRepoImpl() {
    }

    @Autowired
    private DatabaseManager databaseManager;

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
    public void deleteDepartmentById(Long id) throws DatabaseException {
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(DELETE_BY_ID)) {
            deleteStatement.setLong(1, id);
            int rs = deleteStatement.executeUpdate();
            LOG.info("Department delete result: {}", rs);
        } catch (SQLException e) {
            LOG.error("Can not delete Department {}\n{}", id, e.getMessage());
            throw new DatabaseException("Fail to delete department!");
        }
    }

    @Override
    public void createDepartment(Department department) throws DatabaseException {
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement createStatement = connection.prepareStatement(CREATE_DEPARTMENT)) {
            createStatement.setString(1, department.getName());
            int rs = createStatement.executeUpdate();
            LOG.info("Department create result: {}", rs);
        } catch (SQLException e) {
            LOG.error("Can not create Department\n{}", e.getMessage());
            throw new DatabaseException("Fail to add new department!");
        }
    }

    @Override
    public boolean checkDepartment(Department department) throws DatabaseException {
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(CHECK_DEPARTMENT)) {
            ResultSet rs;
            checkStatement.setString(1, department.getName());
            checkStatement.setLong(2, department.getID());
            rs = checkStatement.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            LOG.error("Can not get Department by Name\n{}", e.getMessage());
            throw new DatabaseException("Fail to check department!");
        }
        return true;
    }

    private Department extractDepartment(ResultSet rs) throws SQLException {
        Department department;

        try {
            department = new Department(Long.parseLong(rs.getString("id")));
            department.setName(rs.getString("name"));
        } catch (SQLException e) {
            LOG.error("Can not extract department\n{}", e.getMessage());
            throw e;
        }

        return department;
    }

    @Override
    public ArrayList<Department> getAllDepartments() throws DatabaseException {
        Statement statement;
        ResultSet rs;
        ArrayList<Department> departments = new ArrayList<>();

        try (Connection connection = databaseManager.getConnection()) {
            statement = connection.createStatement();

            rs = statement.executeQuery(GET_ALL_DEPARTMENTS);

            while (rs.next()) {
                departments.add(extractDepartment(rs));
            }

        } catch (SQLException e) {
            LOG.error("Can not get Departments\n{}", e.getMessage());
            throw new DatabaseException("Fail to get departments!");
        }

        return departments;
    }

    @Override
    public void updateDepartment(Department department) throws DatabaseException {
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            updateStatement.setString(1, department.getName());
            updateStatement.setLong(2, department.getID());
            int rs = updateStatement.executeUpdate();
            LOG.info("Department update result: {}", rs);
        } catch (SQLException e) {
            LOG.error("Can not update department\n{}", e.getMessage());
            throw new DatabaseException("Fail to edit department!");
        }
    }

}
