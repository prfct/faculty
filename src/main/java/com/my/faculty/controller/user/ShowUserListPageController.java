package com.my.faculty.controller.user;

import com.my.faculty.common.Key;
import com.my.faculty.common.Page;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.domain.User;
import com.my.faculty.service.UserService;
import com.my.faculty.service.impl.UserServiceImpl;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class ShowUserListPageController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        Set<User> users = userService.showUserList();
        if (users != null) {
            LOGGER.info("Controller.Success get user list, size= '{}'", users.size());
            model.setAttribute(Key.USERS, users);
        }
        return Page.USER_LIST;
    }
}
