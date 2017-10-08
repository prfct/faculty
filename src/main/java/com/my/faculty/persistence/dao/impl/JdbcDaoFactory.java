package com.my.faculty.persistence.dao.impl;

import com.my.faculty.persistence.dao.*;
import com.my.faculty.persistence.dao.DaoConnection;
import com.my.faculty.persistence.dao.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import static java.util.Objects.isNull;

/**
 * @author Oleksii Petrokhalko.
 */
public class JdbcDaoFactory extends DaoFactory {
    private static final String CONNECTION_CAN_NOT_BE_NULL = "Connection can not be null";
    private static final String CONNECTION_IS_NOT_AN_ABSTRACT_CONNECTION_IMPL_FOR_JDBC =
            "Connection is not an JdbcDaoConnection for JDBC";
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private DataSource dataSource;

    public JdbcDaoFactory() {
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/faculty");
        } catch (NamingException e) {
            LOGGER.error("Error in looking up the data source: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public DaoConnection getDaoConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            LOGGER.error("Error during the DaoConnection getting: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public CourseDao getCourseDao(DaoConnection connection) {
        checkConnection(connection);
        return new CourseDaoImpl(getSqlConnection(connection));
    }

    @Override
    public UserDao getUserDao(DaoConnection connection) {
        checkConnection(connection);
        return new UserDaoImpl(getSqlConnection(connection));
    }

    @Override
    public StudentDao getStudentDao(DaoConnection connection) {
        checkConnection(connection);
        return new StudentDaoImpl(getSqlConnection(connection));
    }

    @Override
    public AuthDao getAuthDao(DaoConnection connection) {
        checkConnection(connection);
        return new AuthDaoImpl(getSqlConnection(connection));
    }

    private void checkConnection(DaoConnection conn) {
        if (isNull(conn)) {
            throw new DaoException(CONNECTION_CAN_NOT_BE_NULL);
        }

        if (!(conn instanceof JdbcDaoConnection)) {
            throw new DaoException(CONNECTION_IS_NOT_AN_ABSTRACT_CONNECTION_IMPL_FOR_JDBC);
        }
    }

    private Connection getSqlConnection(DaoConnection conn) {
        return ((JdbcDaoConnection) conn).getConnection();
    }

}
