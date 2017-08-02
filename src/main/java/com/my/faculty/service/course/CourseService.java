package com.my.faculty.service.course;

import com.my.faculty.domain.Course;
import com.my.faculty.domain.CourseStudent;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public interface CourseService {
    Course createCourse();

    List<CourseStudent> showCreateCoursePage();

    List<Course> showCourseListPage();

}
