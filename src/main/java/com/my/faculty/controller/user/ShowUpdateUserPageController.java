package com.my.faculty.controller.user;

import com.my.faculty.common.Key;
import com.my.faculty.common.Redirect;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.service.UserService;
import com.my.faculty.service.impl.UserServiceImpl;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.my.faculty.common.Key.ROLES;
import static com.my.faculty.common.Key.USER;
import static com.my.faculty.common.Page.USER_UPDATE;

public class ShowUpdateUserPageController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        Long id = Long.parseLong(model.findParameter(Key.ID));
        User user = userService.read(id);
        if (user != null) {
            model.setAttribute(USER, user);
            model.setAttribute(ROLES, UserRole.values());
            LOGGER.info("Controller.Success get user, id= '{}'", id);
            return USER_UPDATE;
        }
        LOGGER.info("Controller.Cant get user, id = '{}'", id);
        return Redirect.USERS_LIST;
    }
}
