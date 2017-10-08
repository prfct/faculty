package com.my.faculty.persistence.dao;

import com.my.faculty.domain.Course;
import com.my.faculty.domain.Student;

import java.util.Set;

/**
 * @author Oleksii Petrokhalko.
 */
public interface CourseDao {
    Course create(Course course);

    Set<Course> readAll();

    Course findById(Long courseId);

    Long findCourseByUser(Student student);

    Set<Course> findCoursesByTeacherId(Long id);
}
