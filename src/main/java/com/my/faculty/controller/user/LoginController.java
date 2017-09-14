package com.my.faculty.controller.user;

import com.my.faculty.common.Page;
import com.my.faculty.common.Redirect;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.controller.parsers.StringParser;
import com.my.faculty.domain.Auth;
import com.my.faculty.service.exception.UserNotExistException;
import com.my.faculty.service.UserService;
import com.my.faculty.service.impl.UserServiceImpl;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.my.faculty.common.Key.*;

/**
 * @author Oleksii Petrokhalko.
 */
public class LoginController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final String INCORRECT_EMAIL_PASSWORD = "login.error.incorrectEmailOrPassword";
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        Map<String, Object> errors = new HashMap<>();
        String email = model.findParameter(EMAIL, new StringParser());
        String password = model.findParameter(PASSWORD, new StringParser());
        try {
            Auth auth = userService.login(email, password);
            model.putSessionAttribute(AUTH, auth);
            LOGGER.info("Controller.Success login user, id = '{}'", auth.getUser().getId());
            return Redirect.COURSE_LIST;
        } catch (UserNotExistException e) {
            errors.put(LOGIN_ERROR, INCORRECT_EMAIL_PASSWORD);
            model.setAttributes(errors);
            LOGGER.warn("Controller.Unsuccess login for User, email = '{}'", email);
            return Page.LOGIN;
        }
    }

}
