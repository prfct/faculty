package com.my.faculty.service.impl;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Course;
import com.my.faculty.persistence.dao.DaoFactory;
import com.my.faculty.persistence.dao.DaoConnection;
import com.my.faculty.service.CourseService;

import java.util.Set;

/**
 * @author Oleksii Petrokhalko.
 */
public class CourseServiceImpl implements CourseService {
    private DaoFactory daoFactory;

    private CourseServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class InstanceHolder {
        private static final CourseServiceImpl INSTANCE = new CourseServiceImpl(DaoFactory.getInstance());
    }

    public static CourseService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Course createCourse(Course course) {
        try (DaoConnection connection = daoFactory.getDaoConnection()) {
            return daoFactory.getCourseDao(connection).create(course);
        }
    }

    @Override
    public Set<Course> getAllCourses() {
        try (DaoConnection connection = daoFactory.getDaoConnection()) {
            return daoFactory.getCourseDao(connection).readAll();
        }
    }

    @Override
    public Course read(Long courseId) {
        try (DaoConnection connection = daoFactory.getDaoConnection()) {
            Course course = daoFactory.getCourseDao(connection).findById(courseId);
            if (course != null) {
                course.setStudents(daoFactory.getStudentDao(connection).findAllByCourse(courseId));
            }
            return course;
        }
    }

    @Override
    public Set<Course> getCoursesByTeacherId(Auth auth) {
        try (DaoConnection connection = daoFactory.getDaoConnection()) {
            return daoFactory.getCourseDao(connection).findCoursesByTeacherId(auth.getId());
        }
    }
}
