package com.my.faculty.controller;

import com.my.faculty.common.Page;
import com.my.faculty.common.Redirect;
import com.my.faculty.controller.parsers.StringParser;
import com.my.faculty.domain.Auth;
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
    private UserService userService;

    LoginController(UserService userService) {
        this.userService = userService;
    }

    private static final class InstanceHolder {
        private static final LoginController INSTANCE = new LoginController(UserServiceImpl.getInstance());
    }

    public static ControllerCommand getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String execute(Model model) {
        Map<String, Object> errors = new HashMap<>();
        String email = model.findParameter(EMAIL, new StringParser());
        String password = model.findParameter(PASSWORD, new StringParser());
        Auth auth = userService.login(email, password);
        if (auth != null) {
            model.putSessionAttribute(AUTH, auth);
            LOGGER.info("Controller.Success login user, id = '{}'", auth.getUser().getId());
            return Redirect.COURSE_LIST;
        }
        errors.put(LOGIN_ERROR, INCORRECT_EMAIL_PASSWORD);
        model.setAttributes(errors);
        LOGGER.info("Controller.Unsuccess login for User, email = '{}'", email);
        return Page.LOGIN;
    }
}
