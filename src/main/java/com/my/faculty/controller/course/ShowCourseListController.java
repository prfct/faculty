package com.my.faculty.controller.course;

import com.my.faculty.common.Key;
import com.my.faculty.common.Page;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.domain.Course;
import com.my.faculty.service.CourseService;
import com.my.faculty.service.impl.CourseServiceImpl;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static com.my.faculty.common.Key.COURSES_ERROR;

/**
 * @author Oleksii Petrokhalko.
 */
public class ShowCourseListController implements ControllerCommand {
    private static final String NO_COURSES = "course.error.list";
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private CourseService courseService = CourseServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        Set<Course> courses = courseService.getAllCourses();
        if (courses != null && !courses.isEmpty()) {
            model.setAttribute(Key.COURSES, courses);
            return Page.COURSE_LIST;
        }
        model.setAttribute(COURSES_ERROR, NO_COURSES);
        LOGGER.info("Controller.There are no courses");
        return Page.COURSE_LIST;
    }
}
