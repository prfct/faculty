package com.my.faculty.persistance.dao.impl;

import com.my.faculty.common.Key;
import com.my.faculty.common.builders.CourseBuilder;
import com.my.faculty.common.builders.StudentBuilder;
import com.my.faculty.common.builders.UserBuilder;
import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Course;
import com.my.faculty.domain.Student;
import com.my.faculty.persistance.dao.StudentDao;
import com.my.faculty.persistance.db.QueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class StudentDaoImpl implements StudentDao {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private Connection connection;

    public StudentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Student create(Student student) {
        String query = "INSERT INTO student (course_id, user_id)VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, student.getCourse().getId());
            preparedStatement.setLong(2, student.getUser().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long generatedId = generatedKeys.getLong(Key.ONE);
                student.setId(generatedId);
            }
            return student;
        } catch (SQLException e) {
            LOGGER.warn("StudentDao.Create student exception {}", e);
            throw new QueryException(e);
        }
    }

    @Override
    public Student findByCourseAndStudentId(Student student) {
        String query = "SELECT * FROM student WHERE course_id = ? AND user_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, student.getCourse().getId());
            preparedStatement.setLong(2, student.getUser().getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            return buildStudent(resultSet);
        } catch (SQLException e) {
            LOGGER.warn("StudentDao.Create student exception {}", e);
            throw new QueryException(e);
        }
    }

    private Student buildStudent(ResultSet resultSet) throws SQLException {
        Student student = null;
        while (resultSet.next()) {
            student = new StudentBuilder()
                    .withId(resultSet.getLong("student_id"))
                    .withMark(resultSet.getLong("mark"))
                    .withFeedback(resultSet.getString("feedback"))
                    .build();
        }
        return student;
    }

    @Override
    public Set<Student> findAllByCourse(Long courseId) {
        String query = "SELECT * FROM student JOIN user ON student.user_id = user.user_id WHERE course_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return buildStudentsSet(resultSet);
        } catch (SQLException e) {
            LOGGER.warn("StudentDao.Create student exception {}", e);
            throw new QueryException(e);
        }
    }

    private Set<Student> buildStudentsSet(ResultSet resultSet) throws SQLException {
        Set<Student> students = new LinkedHashSet<>();
        while (resultSet.next()) {
            Student student = new StudentBuilder()
                    .withId(resultSet.getLong("student_id"))
                    .withMark(resultSet.getLong("mark"))
                    .withFeedback(resultSet.getString("feedback"))
                    .withUser(new UserBuilder()
                            .withUsername(resultSet.getString("username"))
                            .withBirthDate(resultSet.getTimestamp("birthDate"))
                            .build())
                    .build();
            students.add(student);
        }
        return students;
    }

    @Override
    public Set<Student> findStudentDetail(Long userId) {
        String query = "SELECT * FROM student JOIN course ON student.course_id = course.course_id " +
                "WHERE student.user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<Student> students = new LinkedHashSet<>();
            while (resultSet.next()) {
                Student student = new StudentBuilder()
                        .withId(resultSet.getLong("student_id"))
                        .withMark(resultSet.getLong("mark"))
                        .withFeedback(resultSet.getString("feedback"))
                        .withCourse(new CourseBuilder()
                                .withId(resultSet.getLong("course_id"))
                                .withTitle(resultSet.getString("title"))
                                .withCreateDate(resultSet.getTimestamp("create_date"))
                                .withStatus(resultSet.getBoolean("course_status"))
                                .build())
                        .build();
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            LOGGER.warn("StudentDao.Create student exception {}", e);
            throw new QueryException(e);
        }
    }

    @Override
    public Student findById(Long studentId) {
        String query = "SELECT * FROM student WHERE student_id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return buildStudent(resultSet);
        } catch (SQLException e) {
            LOGGER.warn("StudentDao.Create student exception {}", e);
            throw new QueryException(e);
        }
    }

    @Override
    public void update(Student student) {
        String query = "UPDATE student SET mark = ?, feedback = ? WHERE student_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, student.getMark());
            preparedStatement.setString(2, student.getFeedback());
            preparedStatement.setLong(3, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("StudentDao.Update student exception {}", e);
            throw new QueryException(e);
        }
    }

    @Override
    public Set<Long> findStudentIdsByTeacher(Long teacherId) {
        String query = "SELECT student_id " +
                "FROM student WHERE course_id = (SELECT course_id  FROM course WHERE user_id = ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, teacherId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<Long> students = new HashSet<>();
            while (resultSet.next()) {
                students.add(resultSet.getLong("student_id"));
            }
            return students;
        } catch (SQLException e) {
            LOGGER.warn("StudentDao.Find student ids exception {}", e);
            throw new QueryException(e);
        }
    }


}
