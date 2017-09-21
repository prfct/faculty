package com.my.faculty.controller.course;

import com.my.faculty.common.Page;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.domain.Course;
import com.my.faculty.service.CourseService;
import com.my.faculty.service.impl.CourseServiceImpl;
import com.my.faculty.web.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.my.faculty.common.Key.COURSES_ERROR;

/**
 * @author Oleksii Petrokhalko.
 */
public class ShowCourseListController implements ControllerCommand {
    private static final String NO_COURSES = "course.error.list";
    private CourseService courseService = CourseServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        List<Course> courses = courseService.showCourseListPage();
        if (courses != null && !courses.isEmpty()) {
            model.setAttribute("courses", courses);
            return Page.COURSE_LIST;
        }
        model.setAttribute(COURSES_ERROR, NO_COURSES);
        return Page.COURSE_LIST;
    }
}
