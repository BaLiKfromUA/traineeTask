package com.aimprosoft.sandbox.database.department;

import com.aimprosoft.sandbox.database.DatabaseManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author BaLiK on 25.03.19
 */
public class DepartmentDAO {
    private static Logger LOG = Logger.getLogger(DepartmentDAO.class);
    /**
     * Resources
     **/
    //todo: add prep. statements
    private Connection connection;
    private PreparedStatement createStatement;

    /**
     * Statements
     * **/
    private static final String GET_ALL_DEPARTMENTS = "SELECT * FROM departments";
    private static final String CREATE_DEPARTMENT = "INSERT INTO departments (name)" +
            " VALUES (?)";

    public DepartmentDAO() throws IOException, SQLException {
        connection = DatabaseManager.getInstance().getConnection();
        createStatement = connection.prepareStatement(CREATE_DEPARTMENT);
    }

    public void createDepartment(Department department){
        try {
            createStatement.setString(1,department.getName());
            int rs = createStatement.executeUpdate();
            LOG.info("Department create result: " + rs);
        } catch (SQLException e) {
            LOG.error("Can not create Department\n" + e.getMessage());
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
        createStatement.close();
    }
}
