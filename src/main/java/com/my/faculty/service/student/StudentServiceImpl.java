package com.my.faculty.service.student;

import com.my.faculty.domain.Student;

public class StudentServiceImpl implements StudentService {

    private StudentServiceImpl() {
    }

    private static class InstanceHolder {
        private static final StudentServiceImpl INSTANCE = new StudentServiceImpl();
    }

    public static StudentService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Student create() {
        return null;
    }
}
