package com.my.faculty.common.builders;

import com.my.faculty.domain.Course;
import com.my.faculty.domain.User;

import java.sql.Timestamp;
import java.time.LocalDate;

public class CourseBuilder implements Builder<Course> {
    private Course course;

    public CourseBuilder() {
        course = new Course();
    }

    public CourseBuilder withId(Long id) {
        course.setId(id);
        return this;
    }

    public CourseBuilder withTitle(String title) {
        course.setTitle(title);
        return this;
    }

    public CourseBuilder withCreateDate(Timestamp createDate) {
        course.setCreateDate(createDate.toLocalDateTime().toLocalDate());
        return this;
    }

    public CourseBuilder withCreateDate(LocalDate localDate){
        course.setCreateDate(localDate);
        return this;
    }

    public CourseBuilder withDefaultDate() {
        course.setCreateDate(LocalDate.now());
        return this;
    }

    public CourseBuilder withTeacher(User user) {
        course.setUser(user);
        return this;
    }

    public CourseBuilder withStatus(Boolean status) {
        course.setActive(status);
        return this;
    }

    public CourseBuilder withDefaultStatus() {
        course.setActive(true);
        return this;
    }

    @Override
    public Course build() {
        return course;
    }
}
