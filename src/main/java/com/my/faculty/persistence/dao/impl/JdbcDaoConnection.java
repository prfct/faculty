package com.my.faculty.persistence.dao.impl;

import com.my.faculty.persistence.dao.DaoConnection;
import com.my.faculty.persistence.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoConnection implements DaoConnection {
    private static final String CAN_NOT_BEGIN_TRANSACTION = "Can not begin transaction.";
    private static final String CAN_NOT_COMMIT_TRANSACTION = "Can not commit transaction";
    private static final String CAN_NOT_ROLLBACK_TRANSACTION = "Can not rollback transaction";
    private static final String CAN_NOT_CLOSE_CONNECTION = "Can not close connection";
    private Connection connection;
    private boolean transactionBegun = false;
    private boolean transactionCommitted = false;

    public JdbcDaoConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
            transactionBegun = true;
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_BEGIN_TRANSACTION, e);
        }
    }

    @Override
    public void commitTransaction() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            transactionCommitted = true;
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_COMMIT_TRANSACTION, e);
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
            transactionCommitted = true;
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_ROLLBACK_TRANSACTION, e);
        }

    }

    @Override
    public void close() {
        try {
            if (transactionBegun && !transactionCommitted) {
                rollbackTransaction();
            }
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_CLOSE_CONNECTION, e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
