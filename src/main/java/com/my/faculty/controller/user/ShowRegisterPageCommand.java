package com.my.faculty.controller.user;

import com.my.faculty.common.Page;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.web.Model;

public class ShowRegisterPageCommand implements ControllerCommand {
    @Override
    public String execute(Model model) {
        return Page.REGISTRATION;
    }
}
