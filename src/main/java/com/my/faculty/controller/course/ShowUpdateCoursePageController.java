package com.my.faculty.controller.course;

import com.my.faculty.common.Key;
import com.my.faculty.common.Page;
import com.my.faculty.common.Redirect;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.domain.Course;
import com.my.faculty.service.CourseService;
import com.my.faculty.service.UserService;
import com.my.faculty.service.impl.CourseServiceImpl;
import com.my.faculty.service.impl.UserServiceImpl;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.my.faculty.common.Key.COURSE;
import static com.my.faculty.common.Key.USERS;


public class ShowUpdateCoursePageController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private CourseService courseService = CourseServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        Long courseId = Long.parseLong(model.findParameter(Key.ID));
        Course course = courseService.read(courseId);
        if (course != null) {
            model.setAttribute(COURSE, course);
            model.setAttribute(USERS, userService.showUserList());
            LOGGER.info("Controller.Success show update page for course, id= '{}'", courseId);
            return Page.COURSE_UPDATE;

        }
        LOGGER.info("Controller.No such course , id= '{}'", courseId);
        return Redirect.COURSE_LIST;
    }
}
