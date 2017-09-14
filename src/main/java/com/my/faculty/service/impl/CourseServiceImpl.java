package com.my.faculty.service.impl;

import com.my.faculty.domain.Course;
import com.my.faculty.domain.Students;
import com.my.faculty.persistance.dao.DaoFactory;
import com.my.faculty.persistance.db.ConnectionPool;
import com.my.faculty.persistance.db.MySqlConnectionPool;
import com.my.faculty.service.CourseService;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public class CourseServiceImpl implements CourseService {
    private DaoFactory df = DaoFactory.getMySqlDaoFactory();
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
    public Course createCourse() {
        return null;
    }

    @Override
    public List<Students> showCreateCoursePage() {
        return null;
    }

    @Override
    public List<Course> showCourseListPage() {
        List<Course> courses = df.getCourseDao(connectionPool.getConnection()).readAll();
        return courses;
    }
}
