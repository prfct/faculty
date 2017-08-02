package com.my.faculty.persistance.dao.course;

import com.my.faculty.domain.Course;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public interface CourseDao {
    Course create();
    List<Course> readAll();
}
