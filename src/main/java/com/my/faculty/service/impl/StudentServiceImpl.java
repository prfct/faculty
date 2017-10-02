package com.my.faculty.service.impl;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Course;
import com.my.faculty.domain.Student;
import com.my.faculty.persistance.dao.DaoFactory;
import com.my.faculty.persistance.db.AbstractConnection;
import com.my.faculty.persistance.db.ConnectionPool;
import com.my.faculty.persistance.db.MySqlConnectionPool;
import com.my.faculty.service.StudentService;

import java.util.Set;

public class StudentServiceImpl implements StudentService {
    private DaoFactory daoFactory = DaoFactory.getMySqlDaoFactory();
    private ConnectionPool connectionPool = MySqlConnectionPool.getInstance();

    private StudentServiceImpl() {
    }

    private static class InstanceHolder {
        private static final StudentServiceImpl INSTANCE = new StudentServiceImpl();
    }

    public static StudentService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Student create(Student student) {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            if (daoFactory.getStudentDao(connection).findByCourseAndStudentId(student) != null
                    || daoFactory.getCourseDao(connection).findTeacherInCourseById(student) != null) {
                return student;
            }
            return daoFactory.getStudentDao(connection).create(student);
        }
    }

    @Override
    public Set<Student> findStudentsByUser(Auth auth) {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            return daoFactory.getStudentDao(connection).findAllByStudentId(auth.getId());
        }
    }

}
