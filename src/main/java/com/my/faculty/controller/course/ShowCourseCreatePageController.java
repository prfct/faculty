package com.my.faculty.controller.course;

import com.my.faculty.common.Key;
import com.my.faculty.common.Page;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.service.UserService;
import com.my.faculty.service.impl.UserServiceImpl;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Oleksii Petrokhalko.
 */
public class ShowCourseCreatePageController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        model.setAttribute(Key.USERS, userService.showUserList());
        LOGGER.info("Controller.Show course create page");
        return Page.COURSE_CREATE;
    }
}
