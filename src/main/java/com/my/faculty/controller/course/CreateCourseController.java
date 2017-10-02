package com.my.faculty.controller.course;

import com.my.faculty.common.Key;
import com.my.faculty.common.Page;
import com.my.faculty.common.Redirect;
import com.my.faculty.common.builders.CourseBuilder;
import com.my.faculty.common.builders.UserBuilder;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.controller.parsers.TitleParser;
import com.my.faculty.domain.Course;
import com.my.faculty.service.CourseService;
import com.my.faculty.service.UserService;
import com.my.faculty.service.impl.CourseServiceImpl;
import com.my.faculty.service.impl.UserServiceImpl;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.my.faculty.common.Key.TEACHER_ID;
import static com.my.faculty.common.Key.TITLE;

/**
 * @author Oleksii Petrokhalko.
 */
public class CreateCourseController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private CourseService courseService = CourseServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        Map<String, Object> errors = new HashMap<>();
        String title = model.findParameter(TITLE, new TitleParser(errors));
        Long id = Long.parseLong(model.findParameter(TEACHER_ID));
        Course course = new CourseBuilder()
                .withTitle(title)
                .withDefaultDate()
                .withDefaultStatus()
                .withTeacher(new UserBuilder()
                        .withId(id)
                        .build())
                .build();
        if (errors.isEmpty()) {
            Course createdCourse = courseService.createCourse(course);
            LOGGER.info("Controller.Course create success'{}'" + createdCourse.getId());
            return Redirect.COURSE_LIST;
        }
        model.setAttributes(errors);
        model.setAttribute(Key.USERS, userService.showUserList());
        LOGGER.info("Controller.Course create unsuccess '{}'" + course.getTitle());
        return Page.COURSE_CREATE;
    }
}
