package com.my.faculty.persistance.dao.student;

import com.my.faculty.domain.Student;

import java.sql.Connection;

public class StudentDaoImpl implements StudentDao {
    private Connection connection;

    public StudentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Student create(Student student) {
        return null;
    }
}
