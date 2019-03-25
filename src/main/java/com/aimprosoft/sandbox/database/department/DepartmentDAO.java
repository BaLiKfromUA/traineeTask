package com.aimprosoft.sandbox.database.department;

import com.aimprosoft.sandbox.database.DatabaseManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
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

    /**
     * Statements
     * **/
    private static final String GET_ALL_DEPARTMENTS = "SELECT * FROM departments";


    public DepartmentDAO() throws IOException, SQLException {
        connection = DatabaseManager.getInstance().getConnection();
    }


    public void closeConnection() throws SQLException {
        connection.close();
    }
}
