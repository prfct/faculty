package com.my.faculty.persistance.dao.impl;

import com.my.faculty.domain.Course;
import com.my.faculty.persistance.dao.CourseDao;

import java.sql.Connection;
import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public class CourseDaoImpl implements CourseDao {
    private Connection connection;

    public CourseDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Course create() {

        return null;
    }

    @Override
    public List<Course> readAll() {

        return null;
    }
}