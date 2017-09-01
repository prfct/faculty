package com.my.faculty.persistance.dao;

import com.my.faculty.domain.Teacher;
import com.my.faculty.persistance.dao.course.CourseDao;
import com.my.faculty.persistance.dao.student.StudentDao;
import com.my.faculty.persistance.dao.teacher.TeacherDao;
import com.my.faculty.persistance.dao.user.UserDao;
import com.my.faculty.persistance.db.AbstractConnection;

/**
 * @author Oleksii Petrokhalko.
 */
public abstract class DaoFactory {
    public abstract CourseDao getCourseDao(AbstractConnection connection);

    public abstract UserDao getUserDao(AbstractConnection connection);

    public abstract TeacherDao getTeacherDao(AbstractConnection connection);

    public abstract StudentDao getStudentDao(AbstractConnection connection);

    public static DaoFactory getMySqlDaoFactory() {
        return MySqlDaoFactory.getInstance();
    }

}
