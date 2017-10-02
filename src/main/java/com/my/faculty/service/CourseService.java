package com.my.faculty.service;

import com.my.faculty.domain.Course;
import com.my.faculty.domain.Student;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public interface CourseService {
    Course createCourse(Course course);

    List<Student> showCreateCoursePage();

    List<Course> showCourseListPage();

    Course read(Long courseId);
}
