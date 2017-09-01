package com.my.faculty.controller.user;

import com.my.faculty.common.Key;
import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.domain.Teacher;
import com.my.faculty.domain.User;
import com.my.faculty.service.teacher.TeacherService;
import com.my.faculty.service.teacher.TeacherServiceImpl;
import com.my.faculty.service.user.UserService;
import com.my.faculty.service.user.UserServiceImpl;
import com.my.faculty.web.Model;

public class CreateTeacherController implements ControllerCommand {
    private TeacherService teacherService = TeacherServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        Teacher teacher = teacherService.create(Long.parseLong(model.findParameter(Key.ID)));

        return null;
    }
}
