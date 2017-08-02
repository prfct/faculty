package com.my.faculty.controller.user;

import com.my.faculty.common.Key;
import com.my.faculty.common.Page;
import com.my.faculty.common.Redirect;
import com.my.faculty.context.ApplicationContext;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.controller.parsers.StringParser;
import com.my.faculty.domain.User;
import com.my.faculty.service.ServiceContext;
import com.my.faculty.service.exception.UserNotExistException;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static com.my.faculty.common.Key.*;
import static com.my.faculty.util.ModelUtil.findLocaleBundle;


/**
 * @author Oleksii Petrokhalko.
 */
public class LoginUserController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final String INCORRECT_EMAIL_PASSWORD = "incorrect.email.password";
    private static final String LOGIN = "login.header.text";
    private static final String REGISTRATION = "registration.header.text";
    private ServiceContext sc = ApplicationContext.getServiceContext();


    @Override
    public String execute(Model model) {
        Map<String, Object> errors = new HashMap<>();
        String email = model.findParameter(EMAIL, new StringParser());
        String password = model.findParameter(PASSWORD, new StringParser());
        ResourceBundle bundle = findLocaleBundle(model);
        try {
            User user = sc.getUserService().loginUser(email, password);
            model.putSessionAttribute(USER, user);
            LOGGER.info("Controller.Success login user, id = '{}'", user.getId());
            return Redirect.COURSE_LIST;
        } catch (UserNotExistException e) {
            errors.put(LOGIN_ERROR, bundle.getString(INCORRECT_EMAIL_PASSWORD));
            model.setAttributes(errors);
            LOGGER.warn("Controller.Unsuccess login for User, email = '{}'", email);
            model.setAttribute(LOGIN_HEADER_TEXT, bundle.getString(LOGIN));
            model.setAttribute(REGISTRATION_HEADER_TEXT, bundle.getString(REGISTRATION));
            return Page.LOGIN;
        }
    }

}
