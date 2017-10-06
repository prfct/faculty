package com.my.faculty.controller;

import com.my.faculty.common.Page;
import com.my.faculty.domain.Auth;
import com.my.faculty.web.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogoutController implements ControllerCommand {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public String execute(Model model) {
        Auth auth = (Auth) model.getSessionAttribute("auth");
        if (auth != null) {
            model.invalidateSession();
            LOGGER.info("Controller.Success logout user, id '{}'" + auth.getId());
        }
        return Page.LOGIN;
    }
}
