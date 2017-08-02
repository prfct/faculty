package com.my.faculty.persistance.dao;

import com.my.faculty.persistance.dao.course.CourseDao;
import com.my.faculty.persistance.dao.user.UserDao;

/**
 * @author Oleksii Petrokhalko.
 */
public class DaoContext {
    private CourseDao courseDao;
    private UserDao userDao;

    public static DaoContext init(DaoFactory daoFactory) {
        DaoContext daoContext = new DaoContext();
        daoContext.courseDao = daoFactory.createCourseDao();
        daoContext.userDao = daoFactory.createUserDao();
        return daoContext;
    }

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
