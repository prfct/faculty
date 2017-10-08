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

import static com.my.faculty.common.Key.ONE;

public class ShowUserListPageController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        int pageNumber = 1;
        String page = model.findParameter(Key.PAGE_NUMBER);
        if (page != null){
            pageNumber = Integer.parseInt(page);
        }
        Set<User> users = userService.showUserList(pageNumber);
        if (users != null) {
            int pagesQuantity = userService.pagesQuantity(users.size());
            LOGGER.info("Controller.Success get user list, size= '{}'", users.size());
            model.setAttribute(Key.USERS, users);
            model.setAttribute(Key.PAGES_QUANTITY, pagesQuantity);
        }
        return Page.USER_LIST;
    }
}
