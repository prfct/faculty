package com.my.faculty.controller.student;

import com.my.faculty.common.Key;
import com.my.faculty.common.Page;
import com.my.faculty.common.Redirect;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.domain.Student;
import com.my.faculty.service.StudentService;
import com.my.faculty.service.impl.StudentServiceImpl;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.my.faculty.common.Key.ID;

public class ShowStudentUpdatePageController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private StudentService studentService = StudentServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        Long studentId = Long.parseLong(model.findParameter(ID));
        Student student = studentService.findStudentById(studentId);
        if (student != null) {
            model.setAttribute(Key.STUDENT, student);
            LOGGER.info("Controller.Success get update page for student '{}'", studentId);
            return Page.STUDENT_UPDATE;
        }
        LOGGER.info("Controller.Cant get update page for student '{}'", studentId);
        return Redirect.MY_STUDENTS;
    }
}
