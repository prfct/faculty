package com.my.faculty.persistance.dao;

import com.my.faculty.persistance.db.AbstractConnection;

/**
 * @author Oleksii Petrokhalko.
 */
public abstract class DaoFactory {
    public abstract CourseDao getCourseDao(AbstractConnection connection);

    public abstract UserDao getUserDao(AbstractConnection connection);

    public abstract StudentDao getStudentDao(AbstractConnection connection);

    public abstract AuthDao getAuthDao(AbstractConnection connection);

    public static DaoFactory getMySqlDaoFactory() {
        return MySqlDaoFactory.getInstance();
    }

}
