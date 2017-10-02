package com.my.faculty.controller.student;

import com.my.faculty.common.Page;
import com.my.faculty.common.Redirect;
import com.my.faculty.common.builders.CourseBuilder;
import com.my.faculty.common.builders.StudentBuilder;
import com.my.faculty.common.builders.UserBuilder;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Student;
import com.my.faculty.service.StudentService;
import com.my.faculty.service.impl.StudentServiceImpl;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.my.faculty.common.Key.AUTH;
import static com.my.faculty.common.Key.COURSES_ERROR;
import static com.my.faculty.common.Key.ID;

public class CreateStudentController implements ControllerCommand {
    private static final String CANT_ASSIGN = "course.error.assign";
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private StudentService studentService = StudentServiceImpl.getInstance();


    @Override
    public String execute(Model model) {
        Long courseId = Long.parseLong(model.findParameter(ID));
        Auth auth = (Auth) model.getSessionAttribute(AUTH);
        Student student = new StudentBuilder()
                .withUser(new UserBuilder()
                        .withId(auth.getId())
                        .build())
                .withCourse(new CourseBuilder()
                        .withId(courseId)
                        .build())
                .build();
        Student createdStudent = studentService.create(student);
        if (createdStudent.getId() == null) {
            LOGGER.info("Controller.User already assigned for this course, user_id = '{}'", auth.getId());
            model.setAttribute(COURSES_ERROR, CANT_ASSIGN);
            return Page.COURSE_LIST;
        }
        LOGGER.info("Controller.Student success assign for this course, student_id = '{}'", createdStudent.getId());
        return Redirect.MY_COURSES;
    }
}
