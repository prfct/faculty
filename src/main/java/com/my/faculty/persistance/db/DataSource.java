package com.my.faculty.persistance.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Oleksii Petrokhalko.
 */
public class DataSource {
    private static DataSource instance = new DataSource();
    private BasicDataSource basicDataSource;


    private DataSource() {
        initDataSource();
    }

    private void initDataSource() {
        basicDataSource = new BasicDataSource();
        Properties properties = new Properties();

        try (InputStream inStream = getClass().getResourceAsStream("/db.properties")) {
            properties.load(inStream);
            basicDataSource.setDriverClassName(properties.getProperty("driver.class"));
        } catch (IOException e) {
            //LOGGER.warn("Some problem was occurred while reading property file " + e);
        }

        basicDataSource.setUrl(properties.getProperty("url"));
        basicDataSource.setUsername(properties.getProperty("user"));
        basicDataSource.setPassword(properties.getProperty("password"));
//
//        basicDataSource.setMinIdle(5);
//        basicDataSource.setMaxIdle(10);
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
        } catch (SQLException e) {
            //LOGGER.warn("Some problem was occurred while getting connection to BD " + e);
        }
        return connection;
    }

    public static DataSource getInstance() {
        return instance;
    }

}
