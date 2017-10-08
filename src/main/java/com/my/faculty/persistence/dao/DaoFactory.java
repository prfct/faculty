package com.my.faculty.persistence.dao;

import com.my.faculty.persistence.dao.exception.DaoException;


/**
 * @author Oleksii Petrokhalko.
 */
public abstract class DaoFactory {
    private static final String FACTORY_CLASS_NAME = "com.my.faculty.persistence.dao.impl.JdbcDaoFactory";
    private static DaoFactory INSTANCE;

    public static DaoFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (DaoFactory.class) {
                if (INSTANCE == null) {
                    try {
                        INSTANCE = (DaoFactory) Class.forName(FACTORY_CLASS_NAME).newInstance();
                    } catch (Exception e) {
                        throw new DaoException(e);
                    }
                }
            }
        }
        return INSTANCE;
    }

    public abstract DaoConnection getDaoConnection();

    public abstract CourseDao getCourseDao(DaoConnection connection);

    public abstract UserDao getUserDao(DaoConnection connection);

    public abstract StudentDao getStudentDao(DaoConnection connection);

    public abstract AuthDao getAuthDao(DaoConnection connection);
}
