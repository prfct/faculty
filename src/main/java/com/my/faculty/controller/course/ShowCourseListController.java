package com.my.faculty.controller.course;

import com.my.faculty.common.Page;
import com.my.faculty.context.ApplicationContext;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.domain.Course;
import com.my.faculty.service.ServiceContext;
import com.my.faculty.web.Model;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public class ShowCourseListController implements ControllerCommand {
    private ServiceContext sc = ApplicationContext.getServiceContext();

    @Override
    public String execute(Model model) {
        List<Course> courses = sc.getCourseService().showCourseListPage();
        model.setAttribute("courses", courses);
        return Page.COURSE_LIST;
    }
}
