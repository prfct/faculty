package com.my.faculty.service;

import com.my.faculty.service.course.CourseService;
import com.my.faculty.service.user.UserService;

/**
 * @author Oleksii Petrokhalko.
 */
public class ServiceContext {
    private CourseService courseService;
    private UserService userService;

    public static ServiceContext init(ServiceFactory serviceFactory) {
        ServiceContext serviceContext = new ServiceContext();
        serviceContext.courseService = serviceFactory.createCourseService();
        serviceContext.userService  = serviceFactory.createUserService();
        return serviceContext;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public UserService getUserService() {
        return userService;
    }
}
