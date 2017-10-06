package com.my.faculty.persistance.dao;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Student;

import java.util.Set;

public interface StudentDao {
    Student create(Student student);

    Student findByCourseAndStudentId(Student student);

    Set<Student> findAllByCourse(Long courseId);

    Set<Student> findStudentDetail(Long userId);

    Student findById(Long studentId);

    void update(Student student);

    Set<Long> findStudentIdsByTeacher(Long teacherId);
}
