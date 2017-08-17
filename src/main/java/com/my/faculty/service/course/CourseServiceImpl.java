package com.my.faculty.service.course;

import com.my.faculty.context.ApplicationContext;
import com.my.faculty.domain.Course;
import com.my.faculty.domain.CourseStudent;
import com.my.faculty.persistance.dao.DaoContext;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public class CourseServiceImpl implements CourseService {
    private DaoContext dc = ApplicationContext.getDaoContext();

    @Override
    public Course createCourse() {
        return null;
    }

    @Override
    public List<CourseStudent> showCreateCoursePage() {
        return null;
    }

    @Override
    public List<Course> showCourseListPage() {
        List<Course> courses = dc.getCourseDao().readAll();
        return courses;
    }
}
