package com.my.faculty.controller.course;

import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.controller.parsers.StringParser;
import com.my.faculty.domain.Students;
import com.my.faculty.service.CourseService;
import com.my.faculty.service.StudentsService;
import com.my.faculty.service.StudentsServiceImpl;
import com.my.faculty.service.impl.CourseServiceImpl;
import com.my.faculty.web.Model;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public class CreateCourseController implements ControllerCommand {
    private CourseService cs = CourseServiceImpl.getInstance();
    private StudentsService ss = StudentsServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        String name = model.findParameter("name", new StringParser());
        List<Long> ids = model.findParameter("studentsIds", new StudentsParser());
        cs.createCourse();
        return null;
    }
}
