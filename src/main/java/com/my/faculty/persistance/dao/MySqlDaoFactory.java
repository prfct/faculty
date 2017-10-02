package com.my.faculty.persistance.dao;

import com.my.faculty.persistance.dao.impl.AuthDaoImpl;
import com.my.faculty.persistance.dao.impl.CourseDaoImpl;
import com.my.faculty.persistance.dao.impl.StudentDaoImpl;
import com.my.faculty.persistance.dao.impl.UserDaoImpl;
import com.my.faculty.persistance.db.AbstractConnection;
import com.my.faculty.persistance.db.AbstractConnectionImpl;
import com.my.faculty.persistance.db.DaoException;

import java.sql.Connection;

import static java.util.Objects.isNull;

/**
 * @author Oleksii Petrokhalko.
 */
public class MySqlDaoFactory extends DaoFactory {
    private static final String CONNECTION_CAN_NOT_BE_NULL = "Connection can not be null";
    private static final String CONNECTION_IS_NOT_AN_ABSTRACT_CONNECTION_IMPL_FOR_JDBC =
            "Connection is not an AbstractConnectionImpl for JDBC";

    private MySqlDaoFactory() {
    }

    private static class InstanceHolder {
        private static final MySqlDaoFactory INSTANCE = new MySqlDaoFactory();
    }

    public static DaoFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public CourseDao getCourseDao(AbstractConnection connection) {
        checkConnection(connection);
        return new CourseDaoImpl(getSqlConnection(connection));
    }

    @Override
    public UserDao getUserDao(AbstractConnection connection) {
        checkConnection(connection);
        return new UserDaoImpl(getSqlConnection(connection));
    }

    @Override
    public StudentDao getStudentDao(AbstractConnection connection) {
        checkConnection(connection);
        return new StudentDaoImpl(getSqlConnection(connection));
    }

    @Override
    public AuthDao getAuthDao(AbstractConnection connection) {
        checkConnection(connection);
        return new AuthDaoImpl(getSqlConnection(connection));
    }

    private void checkConnection(AbstractConnection conn) {
        if (isNull(conn)) {
            throw new DaoException(CONNECTION_CAN_NOT_BE_NULL);
        }

        if (!(conn instanceof AbstractConnectionImpl)) {
            throw new DaoException(CONNECTION_IS_NOT_AN_ABSTRACT_CONNECTION_IMPL_FOR_JDBC);
        }
    }

    private Connection getSqlConnection(AbstractConnection conn) {
        return ((AbstractConnectionImpl) conn).getConnection();
    }

}
