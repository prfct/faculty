package com.my.faculty.controller.user;

import com.my.faculty.controller.ControllerCommand;
import com.my.faculty.service.student.StudentService;
import com.my.faculty.service.student.StudentServiceImpl;
import com.my.faculty.service.teacher.TeacherService;
import com.my.faculty.service.teacher.TeacherServiceImpl;
import com.my.faculty.web.Model;

public class CreateStudentController implements ControllerCommand {
    private StudentService studentService = StudentServiceImpl.getInstance();

    @Override
    public String execute(Model model) {
        return null;
    }
}
