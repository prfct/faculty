package com.my.faculty.service;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Student;

import java.util.Set;

public interface StudentService {

    Student create(Student student);

    Set<Student> findStudentsByUser(Auth auth);
}
