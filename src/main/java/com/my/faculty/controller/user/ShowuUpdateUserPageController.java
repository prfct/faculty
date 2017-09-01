package com.my.faculty.controller.user;

import com.my.faculty.common.Key;
import com.my.faculty.common.Page;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.service.user.UserService;
import com.my.faculty.service.user.UserServiceImpl;
import com.my.faculty.web.Model;

public class ShowuUpdateUserPageController implements ControllerCommand {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        User user = userService.read(Long.parseLong(model.findParameter(Key.ID)));
        User authUser = (User) model.getSessionAttribute("user");
        if (user != null && authUser.getUserRole() == UserRole.ADMIN) {
            model.setAttribute("user", user);
            model.setAttribute("roles", UserRole.values());
            return Page.SET_STUDENT;
        }
        return Page.USER_LIST;
    }
}
