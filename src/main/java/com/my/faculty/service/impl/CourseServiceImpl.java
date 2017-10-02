package com.my.faculty.service.impl;

import com.my.faculty.domain.Course;
import com.my.faculty.domain.Student;
import com.my.faculty.persistance.dao.DaoFactory;
import com.my.faculty.persistance.db.AbstractConnection;
import com.my.faculty.persistance.db.ConnectionPool;
import com.my.faculty.persistance.db.MySqlConnectionPool;
import com.my.faculty.service.CourseService;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public class CourseServiceImpl implements CourseService {
    private DaoFactory daoFactory = DaoFactory.getMySqlDaoFactory();
    private ConnectionPool connectionPool = MySqlConnectionPool.getInstance();

    private CourseServiceImpl() {
    }

    private static class InstanceHolder {
        private static final CourseServiceImpl INSTANCE = new CourseServiceImpl();
    }

    public static CourseService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Course createCourse(Course course) {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            return daoFactory.getCourseDao(connection).create(course);
        }
    }

    @Override
    public List<Student> showCreateCoursePage() {
        return null;
    }

    @Override
    public List<Course> showCourseListPage() {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            return daoFactory.getCourseDao(connection).readAll();
        }
    }

    @Override
    public Course read(Long courseId) {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            Course course = daoFactory.getCourseDao(connection).findById(courseId);
            course.setStudents(daoFactory.getStudentDao(connection).findAllByCourse(courseId));
            return course;
        }
    }
}
