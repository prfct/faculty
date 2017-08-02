package com.my.faculty.persistance.dao;

import com.my.faculty.persistance.dao.course.CourseDao;
import com.my.faculty.persistance.dao.user.UserDao;

/**
 * @author Oleksii Petrokhalko.
 */
public interface DaoFactory {
    CourseDao createCourseDao();
    UserDao createUserDao();

}
