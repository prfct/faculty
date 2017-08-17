package com.my.faculty.persistance.db;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Oleksii Petrokhalko.
 */
public class ConnectionManager {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static ConnectionManager instance = new ConnectionManager();
    private DataSource dataSource = DataSource.getInstance();
    private ThreadLocal<Connection> threadConnection = new ThreadLocal<>();

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        return instance;
    }

    Connection getOrCreateConnection() {
        try {
            Connection connection = threadConnection.get();
            if (!isConnectionActive(connection)) {
                connection = dataSource.getConnection();
                connection.setAutoCommit(true);
                threadConnection.set(connection);
            }
            return connection;
        } catch (SQLException e) {
            LOGGER.warn("could get connection", e);
            throw new ConnectionManagerException(e);
        }
    }


    public void commit() {
        try {
            Connection connection = threadConnection.get();
            if (isConnectionActive(connection)) {
                connection.commit();
            }
        } catch (SQLException e) {
            LOGGER.warn("Commit exception", e);
        }
    }

    public void rollback() {
        try {
            Connection connection = threadConnection.get();
            if (isConnectionActive(connection)) {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOGGER.warn("Rollback exception", e);
        }
    }

    public void close() {
        try {
            Connection connection = threadConnection.get();
            if (isConnectionActive(connection)) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.warn("Close exception", e);
        }
    }

    private boolean isConnectionActive(Connection connection) throws SQLException {
        return connection != null && !connection.isClosed();
    }
}
