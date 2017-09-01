package com.my.faculty.persistance.dao;

import com.my.faculty.persistance.dao.course.CourseDao;
import com.my.faculty.persistance.dao.course.CourseDaoImpl;
import com.my.faculty.persistance.dao.student.StudentDao;
import com.my.faculty.persistance.dao.student.StudentDaoImpl;
import com.my.faculty.persistance.dao.teacher.TeacherDao;
import com.my.faculty.persistance.dao.teacher.TeacherDaoImpl;
import com.my.faculty.persistance.dao.user.UserDao;
import com.my.faculty.persistance.dao.user.UserDaoImpl;
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
    public TeacherDao getTeacherDao(AbstractConnection connection) {
        checkConnection(connection);
        return new TeacherDaoImpl(getSqlConnection(connection));
    }

    @Override
    public StudentDao getStudentDao(AbstractConnection connection) {
        checkConnection(connection);
        return new StudentDaoImpl(getSqlConnection(connection));
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
