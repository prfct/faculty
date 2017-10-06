package com.my.faculty.controller.user;

import com.my.faculty.common.Page;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Course;
import com.my.faculty.service.CourseService;
import com.my.faculty.service.impl.CourseServiceImpl;
import com.my.faculty.web.Model;

import java.util.Set;

import static com.my.faculty.common.Key.AUTH;
import static com.my.faculty.common.Key.COURSES;

public class ShowTeacherStudentsPageController implements ControllerCommand {
    private CourseService courseService = CourseServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        Auth auth = (Auth) model.getSessionAttribute(AUTH);
        Set<Course> courses = courseService.getCoursesByTeacherId(auth);
        model.setAttribute(COURSES, courses);
        return Page.MY_STUDENTS;
    }
}
