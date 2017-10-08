package com.my.faculty.persistence.dao;

import com.my.faculty.persistence.dao.exception.DaoException;


/**
 * @author Oleksii Petrokhalko.
 */
public abstract class DaoFactory {
    private static final String FACTORY_CLASS_NAME = "com.my.faculty.persistence.dao.impl.JdbcDaoFactory";
    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {
                    try {
                        instance = (DaoFactory) Class.forName(FACTORY_CLASS_NAME).newInstance();
                    } catch (Exception e) {
                        throw new DaoException(e);
                    }
                }
            }
        }
        return instance;
    }

    public abstract DaoConnection getDaoConnection();

    public abstract CourseDao getCourseDao(DaoConnection connection);

    public abstract UserDao getUserDao(DaoConnection connection);

    public abstract StudentDao getStudentDao(DaoConnection connection);

    public abstract AuthDao getAuthDao(DaoConnection connection);
}
