package com.my.faculty.controller.user;

import com.my.faculty.common.Page;
import com.my.faculty.context.ApplicationContext;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.service.ServiceContext;
import com.my.faculty.web.Model;

/**
 * @author Oleksii Petrokhalko.
 */
public class ShowLoginPageController implements ControllerCommand {
    private ServiceContext sc  = ApplicationContext.getServiceContext();

    @Override
    public String execute(Model model) {
        return Page.LOGIN;
    }

}
