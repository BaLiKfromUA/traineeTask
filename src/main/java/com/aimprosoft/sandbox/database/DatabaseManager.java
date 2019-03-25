package com.aimprosoft.sandbox.database;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author BaLiK on 25.03.19
 */
public class DatabaseManager {
    /**
     * Config
     **/
    //todo:documentation
    private final String DB_URL;
    private final String USERNAME;
    private final String PASSWORD;

    private static Logger LOG = Logger.getLogger(DatabaseManager.class);
    private static DatabaseManager instance;

    private DatabaseManager() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("database.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        DB_URL = properties.getProperty("url");
        USERNAME = properties.getProperty("username");
        PASSWORD = properties.getProperty("password");

        addMySQLToClassPath();
        createTables();
        LOG.info("Database info entered!");
    }

    public static DatabaseManager getInstance() throws IOException {
        if (instance == null) {
            instance = new DatabaseManager();
            LOG.info("DatabaseManager instance created.");
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    private void addMySQLToClassPath() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LOG.error("Add MySQL error!");
        }
    }

    private void createTables() {
        //todo:create tables for user and departments
    }
}
