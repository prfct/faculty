package com.my.faculty.common;

/**
 * @author Oleksii Petrokhalko.
 */
public interface Page {
    String LOGIN = "/WEB-INF/views/login.jsp";
    String REGISTRATION = "/WEB-INF/views/registration.jsp";
    String USER_LIST = "/WEB-INF/views/user_list.jsp";
    String BAD_URL = "/WEB-INF/views/404.jsp";
    String ERROR = "/WEB-INF/views/error.jsp";
    String COURSE_CREATE = "/WEB-INF/views/course_create.jsp";
    String USER_UPDATE = "/WEB-INF/views/user_update.jsp";
    String COURSE_UPDATE = "/WEB-INF/views/course_detail.jsp";
    String COURSE_LIST = "/WEB-INF/views/course_list.jsp";
    String MY_COURSES = "/WEB-INF/views/my_courses.jsp";
    String MY_STUDENTS = "/WEB-INF/views/my_students.jsp";
    String STUDENT_UPDATE = "/WEB-INF/views/student_update.jsp";
    String NOT_ACCESS = "/WEB-INF/views/not_access.jsp";
}
