package com.my.faculty.service;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Course;
import com.my.faculty.domain.Student;

import java.util.List;
import java.util.Set;

/**
 * @author Oleksii Petrokhalko.
 */
public interface CourseService {
    Course createCourse(Course course);

    Set<Course> getAllCourses();

    Course read(Long courseId);

    Set<Course> getCoursesByTeacherId(Auth auth);
}
