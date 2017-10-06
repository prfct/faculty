package com.my.faculty.service.impl;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Student;
import com.my.faculty.persistance.dao.DaoFactory;
import com.my.faculty.persistance.dao.StudentDao;
import com.my.faculty.persistance.db.AbstractConnection;
import com.my.faculty.persistance.db.ConnectionPool;
import com.my.faculty.persistance.db.MySqlConnectionPool;
import com.my.faculty.service.StudentService;
import com.my.faculty.service.exception.StudentAccessException;

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
            StudentDao studentDao = daoFactory.getStudentDao(connection);
            if (studentDao.findByCourseAndStudentId(student) != null
                    || daoFactory.getCourseDao(connection).findCourseByUser(student) != null) {
                return student;
            }
            return studentDao.create(student);
        }
    }

    @Override
    public Set<Student> findStudentCourses(Auth auth) {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            return daoFactory.getStudentDao(connection).findStudentDetail(auth.getId());
        }
    }

    @Override
    public Student findStudentById(Long studentId) {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            return daoFactory.getStudentDao(connection).findById(studentId);
        }
    }

    @Override
    public void update(Student student, Auth auth) throws StudentAccessException {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            Long teacherId = auth.getId();
            Set<Long> students = daoFactory.getStudentDao(connection).findStudentIdsByTeacher(teacherId);
            if (students == null || !students.contains(teacherId)) {
                throw new StudentAccessException();

            }

            daoFactory.getStudentDao(connection).update(student);
        }
    }

}
