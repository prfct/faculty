package com.my.faculty.persistance.dao.course;

import com.my.faculty.domain.Course;
import com.my.faculty.domain.Teacher;
import com.my.faculty.persistance.dao.AbstractDao;
import com.my.faculty.persistance.dao.teacher.SelectTeacherByCourseIdCommand;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public class CourseDaoImpl extends AbstractDao implements CourseDao {

    @Override
    public Course create() {
        return null;
    }

    @Override
    public List<Course> readAll() {
        List<Course> courses = executeDataCommand(new SelectAllCourseCommand());
        for (Course course : courses) {
            Teacher teacher = executeDataCommand(new SelectTeacherByCourseIdCommand(course.getId()));
            course.setTeacher(teacher);
        }
        return courses;
    }
}
