package com.my.faculty.persistance.dao.impl;

import com.my.faculty.common.Key;
import com.my.faculty.common.builders.CourseBuilder;
import com.my.faculty.common.builders.StudentBuilder;
import com.my.faculty.common.builders.UserBuilder;
import com.my.faculty.domain.Course;
import com.my.faculty.domain.Student;
import com.my.faculty.persistance.dao.StudentDao;
import com.my.faculty.persistance.db.QueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        String query = "SELECT * FROM student WHERE course_id =? AND user_id = ?";

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
    public List<Student> findAllByCourse(Long courseId) {
        String query = "SELECT * FROM student JOIN user ON student.user_id = user.user_id WHERE course_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return buildStudentsList(resultSet);
        } catch (SQLException e) {
            LOGGER.warn("StudentDao.Create student exception {}", e);
            throw new QueryException(e);
        }
    }

    private List<Student> buildStudentsList(ResultSet resultSet) throws SQLException {
        List<Student> students = new ArrayList<>();
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
    public Set<Student> findAllByStudentId(Long userId) {
        String query = "SELECT * FROM student JOIN course ON student.course_id = course.course_id " +
                "WHERE student.user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<Student> students = new HashSet<>();
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
}
