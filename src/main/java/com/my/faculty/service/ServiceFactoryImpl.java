package com.my.faculty.service;

import com.my.faculty.service.course.CourseService;
import com.my.faculty.service.course.CourseServiceImpl;
import com.my.faculty.service.user.UserService;
import com.my.faculty.service.user.UserServiceImpl;

/**
 * @author Oleksii Petrokhalko.
 */
public class ServiceFactoryImpl implements ServiceFactory {


    @Override
    public CourseService createCourseService() {
        return new CourseServiceImpl();
    }

    @Override
    public UserService createUserService() {
        return new UserServiceImpl();
    }
}
