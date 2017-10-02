package com.my.faculty.common.builders;

public abstract class AbstractCreator {
    public UserBuilder getUserCreator() {
        return new UserBuilder();
    }

    public AuthBuilder getAuthCreator() {
        return new AuthBuilder();
    }

    public CourseBuilder getCourseBuilder() {
        return new CourseBuilder();
    }

    public StudentBuilder getStudentBuilder(){
        return new StudentBuilder();
    }
}
