package com.my.faculty.common.builders;

import com.my.faculty.domain.Course;
import com.my.faculty.domain.Student;
import com.my.faculty.domain.User;

public class StudentBuilder implements Builder<Student> {
    private Student student;

    public StudentBuilder() {
        student = new Student();
    }

    public StudentBuilder withId(Long id) {
        student.setId(id);
        return this;
    }

    public StudentBuilder withMark(Long mark) {
        student.setMark(mark);
        return this;
    }

    public StudentBuilder withFeedback(String feedback) {
        student.setFeedback(feedback);
        return this;
    }

    public StudentBuilder withUser(User user) {
        student.setUser(user);
        return this;
    }

    public StudentBuilder withCourse(Course course) {
        student.setCourse(course);
        return this;
    }

    @Override
    public Student build() {
        return student;
    }
}
