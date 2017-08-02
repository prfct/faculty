package com.my.faculty.service;

import com.my.faculty.service.course.CourseService;
import com.my.faculty.service.user.UserService;

/**
 * @author Oleksii Petrokhalko.
 */
public interface ServiceFactory {
    CourseService createCourseService();
    UserService createUserService();
}
