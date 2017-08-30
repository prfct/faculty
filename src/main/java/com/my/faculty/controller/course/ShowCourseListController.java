package com.my.faculty.controller.course;

import com.my.faculty.common.Page;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.domain.Course;
import com.my.faculty.service.course.CourseService;
import com.my.faculty.service.course.CourseServiceImpl;
import com.my.faculty.web.Model;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public class ShowCourseListController implements ControllerCommand {
    private CourseService cs = CourseServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        List<Course> courses = cs.showCourseListPage();
        model.setAttribute("courses", courses);
        return Page.COURSE_LIST;
    }
}
