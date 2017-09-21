package com.my.faculty.controller;

import com.my.faculty.common.Page;
import com.my.faculty.common.builders.AuthBuilder;
import com.my.faculty.common.builders.UserBuilder;
import com.my.faculty.controller.parsers.BirthDateParser;
import com.my.faculty.controller.parsers.EmailParser;
import com.my.faculty.controller.parsers.NameParser;
import com.my.faculty.controller.parsers.PasswordParser;
import com.my.faculty.domain.Auth;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.service.UserService;
import com.my.faculty.service.exception.UserExistException;
import com.my.faculty.service.impl.UserServiceImpl;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.my.faculty.common.Key.*;

/**
 * @author Oleksii Petrokhalko.
 */
public class RegistrationController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final String USER_ALREADY_CREATED = "login.error.userAlreadyCreated";
    private UserService us;

    RegistrationController(UserService us) {
        this.us = us;
    }

    private static final class InstanceHolder {
        private static final RegistrationController INSTANCE = new RegistrationController(UserServiceImpl.getInstance());
    }

    public static ControllerCommand getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String execute(Model model) {
        Map<String, Object> errors = new HashMap<>();
        String username = model.findParameter(USERNAME, new NameParser(errors));
        String email = model.findParameter(EMAIL, new EmailParser(errors));
        String password = model.findParameter(PASSWORD, new PasswordParser(errors));
        LocalDate birthDate = model.findParameter(BIRTHDAY, new BirthDateParser(errors));
        User user = new UserBuilder()
                .withUsername(username)
                .withBirthDate(birthDate)
                .withAuth(new AuthBuilder()
                        .withEmail(email)
                        .withPassword(password)
                        .withRole(UserRole.STUDENT)
                        .build())
                .build();
        if (errors.isEmpty()) {
            try {
                User createdUser = us.createUser(user);
                LOGGER.info("Controller.Created new User, id = '{}'", createdUser.getId());
                return Page.LOGIN;
            } catch (UserExistException e) {
                errors.put(REGISTRATION_ERROR, USER_ALREADY_CREATED);
            }
        }
        model.setAttributes(errors);
        model.setAttribute("user", user);
        LOGGER.info("Controller.Couldn't create User, email = '{}', username = '{}'", email, username);
        return Page.REGISTRATION;
    }

}
