package com.my.faculty.controller.user;

import com.my.faculty.common.Redirect;
import com.my.faculty.common.builders.AuthBuilder;
import com.my.faculty.common.builders.UserBuilder;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.controller.parsers.BirthDateParser;
import com.my.faculty.controller.parsers.NameParser;
import com.my.faculty.controller.parsers.UserRoleParser;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.service.UserService;
import com.my.faculty.service.impl.UserServiceImpl;
import com.my.faculty.web.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.my.faculty.common.Key.*;
import static com.my.faculty.common.Page.USER_UPDATE;

public class UpdateUserController implements ControllerCommand {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        Map<String, Object> errors = new HashMap<>();
        Long userId = Long.parseLong(model.findParameter(ID));
        String username = model.findParameter(USERNAME, new NameParser(errors));
        LocalDate birthday = model.findParameter(BIRTHDAY, new BirthDateParser(errors));
        UserRole userRole = model.findParameter(USER_ROLE, new UserRoleParser());
        User user = new UserBuilder()
                .withId(userId)
                .withUsername(username)
                .withBirthDate(birthday)
                .build();
        if (errors.isEmpty()) {
            userService.update(user, userRole);
            return Redirect.USERS_LIST;
        }
        model.setAttribute(USER, user);
        model.setAttribute(ROLES, UserRole.values());
        model.setAttributes(errors);
        return USER_UPDATE;
    }
}
