package com.my.faculty.service.impl;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Student;
import com.my.faculty.persistence.dao.DaoFactory;
import com.my.faculty.persistence.dao.StudentDao;
import com.my.faculty.persistence.dao.DaoConnection;
import com.my.faculty.service.StudentService;
import com.my.faculty.service.exception.StudentAccessException;

import java.util.Set;

public class StudentServiceImpl implements StudentService {
    private DaoFactory daoFactory;

    private StudentServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;

    }

    private static class InstanceHolder {
        private static final StudentServiceImpl INSTANCE = new StudentServiceImpl(DaoFactory.getInstance());
    }

    public static StudentService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Student create(Student student) {
        try (DaoConnection connection =  daoFactory.getDaoConnection()) {
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
        try (DaoConnection connection = daoFactory.getDaoConnection()) {
            return daoFactory.getStudentDao(connection).findStudentDetail(auth.getId());
        }
    }

    @Override
    public Student findStudentById(Long studentId, Auth auth) throws StudentAccessException {
        try (DaoConnection connection = daoFactory.getDaoConnection()) {
            Set<Long> students = daoFactory.getStudentDao(connection).findStudentIdsByTeacher(auth.getId());
            if (students == null || !students.contains(studentId)) {
                throw new StudentAccessException();
            }
            return daoFactory.getStudentDao(connection).findById(studentId);
        }
    }

    @Override
    public void update(Student student, Auth auth)  {
        try (DaoConnection connection = daoFactory.getDaoConnection()) {
            daoFactory.getStudentDao(connection).update(student);
        }
    }

}
