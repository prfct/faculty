package com.my.faculty.persistance.dao;

import com.my.faculty.domain.Course;
import com.my.faculty.domain.Student;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public interface CourseDao {
    Course create(Course course);

    List<Course> readAll();

    Course findById(Long courseId);

    Long findTeacherInCourseById(Student student);

}
