package com.my.faculty.persistance.dao;

import com.my.faculty.domain.Course;
import com.my.faculty.domain.Student;

import java.util.List;
import java.util.Set;

public interface StudentDao {
    Student create(Student student);

    Student findByCourseAndStudentId(Student student);

    List<Student> findAllByCourse(Long courseId);

    Set<Student> findAllByStudentId(Long userId);
}
