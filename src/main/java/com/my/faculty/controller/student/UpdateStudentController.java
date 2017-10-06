package com.my.faculty.controller.student;

import com.my.faculty.common.Key;
import com.my.faculty.common.Page;
import com.my.faculty.common.Redirect;
import com.my.faculty.common.builders.StudentBuilder;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.controller.parsers.MarkParser;
import com.my.faculty.controller.parsers.StringParser;
import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Student;
import com.my.faculty.service.StudentService;
import com.my.faculty.service.exception.StudentAccessException;
import com.my.faculty.service.impl.StudentServiceImpl;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.my.faculty.common.Key.AUTH;
import static com.my.faculty.common.Key.ID;
import static com.my.faculty.common.Key.STUDENT;

public class UpdateStudentController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private StudentService studentService = StudentServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        Map<String, Object> errors = new HashMap<>();
        Long studentId = Long.parseLong(model.findParameter(ID));
        Long mark = model.findParameter(Key.MARK, new MarkParser(errors));
        String feedback = model.findParameter(Key.FEEDBACK, new StringParser());
        Auth auth = (Auth) model.getSessionAttribute(AUTH);
        Student student = new StudentBuilder()
                .withId(studentId)
                .withMark(mark)
                .withFeedback(feedback)
                .build();
        if (errors.isEmpty()) {
            try {
                studentService.update(student, auth);
                model.setAttribute(STUDENT, student);
                LOGGER.info("Controller.Success update student '{}'", studentId);
                return Redirect.MY_STUDENTS;
            } catch (StudentAccessException e) {
                return Page.NOT_ACCESS;
            }
        }
        model.setAttributes(errors);
        model.setAttribute(STUDENT, student);
        LOGGER.info("Controller.Cant update student '{}'", studentId);
        return Page.STUDENT_UPDATE;
    }
}
