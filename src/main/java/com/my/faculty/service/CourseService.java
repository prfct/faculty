package com.my.faculty.service;

import com.my.faculty.domain.Course;
import com.my.faculty.domain.Students;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public interface CourseService {
    Course createCourse();

    List<Students> showCreateCoursePage();

    List<Course> showCourseListPage();

}
