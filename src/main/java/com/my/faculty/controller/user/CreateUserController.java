package com.my.faculty.controller.user;

import com.my.faculty.common.Key;
import com.my.faculty.common.Page;
import com.my.faculty.common.Redirect;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.controller.parsers.EmailParser;
import com.my.faculty.controller.parsers.NameParser;
import com.my.faculty.controller.parsers.PasswordParser;
import com.my.faculty.domain.User;
import com.my.faculty.service.exception.UserExistException;
import com.my.faculty.service.user.UserService;
import com.my.faculty.service.user.UserServiceImpl;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.my.faculty.common.Key.EMAIL;
import static com.my.faculty.common.Key.PASSWORD;
import static com.my.faculty.common.Key.USERNAME;

/**
 * @author Oleksii Petrokhalko.
 */
public class CreateUserController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private UserService us = UserServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        Map<String, Object> errors = new HashMap<>();
        String email = model.findParameter(EMAIL, new EmailParser(errors));
        String username = model.findParameter(USERNAME, new NameParser(errors));
        String password = model.findParameter(PASSWORD, new PasswordParser(errors));
        if (errors.isEmpty()) {
            try {
                User createdUser = us.createUser(new User(username, email, password));
                LOGGER.info("Controller.Created new User, id = '{}'", createdUser.getId());
                return Redirect.LOGIN;
            } catch (UserExistException e) {
                errors.put("registration_error", "login.error.userAlreadyCreated");
            }
        }
        model.setAttributes(errors);
        model.setAttribute(Key.SHOW_REGISTER_FORM, true);
        LOGGER.info("Controller.Couldn't create User, email = '{}', username = '{}'", email, username);
        return Page.LOGIN;
    }

}
