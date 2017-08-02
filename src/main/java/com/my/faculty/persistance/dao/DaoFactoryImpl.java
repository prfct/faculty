package com.my.faculty.persistance.dao;

import com.my.faculty.persistance.dao.course.CourseDao;
import com.my.faculty.persistance.dao.course.CourseDaoImpl;
import com.my.faculty.persistance.dao.user.UserDao;
import com.my.faculty.persistance.dao.user.UserDaoImpl;

/**
 * @author Oleksii Petrokhalko.
 */
public class DaoFactoryImpl implements DaoFactory {
    @Override
    public CourseDao createCourseDao() {
        return new CourseDaoImpl();
    }

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl();
    }
}
