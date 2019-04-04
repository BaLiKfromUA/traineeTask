package com.aimprosoft.sandbox.util.database;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author BaLiK on 25.03.19
 */
@Component
public class DatabaseManager {
    /**
     * Config
     **/
    private final String DB_URL;
    private final String USERNAME;
    private final String PASSWORD;

    private static Logger LOG = LogManager.getLogger(DatabaseManager.class);

    /**
     * Statements
     **/
    private static final String CREATE_DEPARTMENT_TABLE = "CREATE TABLE IF NOT EXISTS departments(" +
            "id BIGINT NOT NULL AUTO_INCREMENT," +
            "name VARCHAR(20) NOT NULL UNIQUE," +
            "PRIMARY KEY(id)" +
            ")";
    private static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE IF NOT EXISTS employees (" +
            "id BIGINT NOT NULL AUTO_INCREMENT," +
            "login VARCHAR(20) NOT NULL, " +
            "email VARCHAR(40) NOT NULL UNIQUE, " +
            "rank INT, " +
            "registration_date TIMESTAMP," +
            "department_id BIGINT NOT NULL," +
            "FOREIGN KEY (department_id) " +
            "REFERENCES departments (id) " +
            "ON DELETE CASCADE," +
            "PRIMARY KEY (id) " +
            ")";


    public DatabaseManager(String url, String user, String password) {

        DB_URL = url;
        USERNAME = user;
        PASSWORD = password;

        addMySQLToClassPath();

        createDepartmentTable();
        createEmployeeTable();

        LOG.info("Database info entered!");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    private void addMySQLToClassPath() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOG.error("Add MySQL error!\n{}", e.getMessage());
        }
    }


    private void createDepartmentTable() {
        try (Connection dbConnection = getConnection();
             Statement statement = dbConnection.createStatement()) {
            statement.execute(CREATE_DEPARTMENT_TABLE);
            LOG.info("Table 'departments' is created!");
        } catch (SQLException e) {
            LOG.error("Creating table 'departments' error!\n{}", e.getMessage());
        }
    }

    private void createEmployeeTable() {
        try (Connection dbConnection = getConnection();
             Statement statement = dbConnection.createStatement()) {
            statement.execute(CREATE_EMPLOYEE_TABLE);
            LOG.info("Table 'employee' is created!");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Creating table 'employee' error!\n{}", e.getMessage());
        }
    }
}
