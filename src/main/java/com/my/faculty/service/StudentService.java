package com.my.faculty.service;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Student;
import com.my.faculty.service.exception.StudentAccessException;

import java.util.Set;

public interface StudentService {

    Student create(Student student);

    Set<Student> findStudentCourses(Auth auth);

    Student findStudentById(Long studentId);

    void update(Student student, Auth auth) throws StudentAccessException;
}
