package com.my.faculty.service.course;

import com.my.faculty.context.ApplicationContext;
import com.my.faculty.domain.Course;
import com.my.faculty.domain.CourseStudent;
import com.my.faculty.persistance.dao.course.CourseDao;
import com.my.faculty.persistance.dao.user.UserDao;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao = ApplicationContext.getDaoContext().getCourseDao();

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
        return null;
    }
}
