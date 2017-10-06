package com.my.faculty.controller.user;

import com.my.faculty.common.Key;
import com.my.faculty.common.Page;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Student;
import com.my.faculty.service.StudentService;
import com.my.faculty.service.impl.StudentServiceImpl;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static com.my.faculty.common.Key.AUTH;

public class ShowStudentCoursesPageController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private StudentService studentService = StudentServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        Auth auth = (Auth) model.getSessionAttribute(AUTH);
        Set<Student> students = studentService.findStudentCourses(auth);
        model.setAttribute(Key.STUDENTS, students);
        LOGGER.info("Controller.Get students info for user '{}'", auth.getId());
        return Page.MY_COURSES;
    }
}
