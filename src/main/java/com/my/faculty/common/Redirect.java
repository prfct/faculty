package com.my.faculty.common;

/**
 * @author Oleksii Petrokhalko.
 */
public interface Redirect {

    String COURSE_LIST = "redirect:/app/course/list";
    String MY_COURSES = "redirect:/app/user/courses";
    String MY_STUDENTS = "redirect:/app/teacher/students";
    String STUDENT_UPDATE = "redirect:/app/student/update?id=";
    String USERS_LIST = "redirect:/app/user/list";
    String USER_UPDATE = "redirect:/app/user/update";
    String REDIRECT = "redirect:";
}
