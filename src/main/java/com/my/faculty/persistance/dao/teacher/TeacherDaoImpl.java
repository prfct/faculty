package com.my.faculty.persistance.dao.teacher;

import com.my.faculty.domain.Teacher;

import java.sql.Connection;

public class TeacherDaoImpl implements TeacherDao {
    private Connection connection;

    public TeacherDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Teacher create(Teacher teacher) {
        return null;
    }
}
