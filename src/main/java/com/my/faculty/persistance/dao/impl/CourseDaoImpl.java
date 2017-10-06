package com.my.faculty.persistance.dao.impl;

import com.my.faculty.common.Key;
import com.my.faculty.common.builders.CourseBuilder;
import com.my.faculty.common.builders.UserBuilder;
import com.my.faculty.domain.*;
import com.my.faculty.persistance.dao.CourseDao;
import com.my.faculty.persistance.db.QueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

/**
 * @author Oleksii Petrokhalko.
 */
public class CourseDaoImpl implements CourseDao {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private Connection connection;

    public CourseDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Course create(Course course) {
        String query = "INSERT INTO course(title, create_date, course_status, user_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(course.getCreateDate().atStartOfDay()));
            preparedStatement.setBoolean(3, course.getActive());
            preparedStatement.setLong(4, course.getUser().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long generatedId = generatedKeys.getLong(Key.ONE);
                course.setId(generatedId);
            }
            return course;
        } catch (SQLException e) {
            LOGGER.warn("CourseDao.Create course exception {}", e);
            throw new QueryException(e);
        }
    }

    @Override
    public Long findCourseByUser(Student student) {
        String query = "SELECT course_id AS courseId FROM course WHERE user_id = ? AND course_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, student.getUser().getId());
            preparedStatement.setLong(2, student.getCourse().getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            Long courseId = null;
            while (resultSet.next()) {
                courseId = resultSet.getLong("courseId");
            }
            return courseId;
        } catch (SQLException e) {
            LOGGER.warn("CourseDao.Find course by id exception {}", e);
            throw new QueryException(e);
        }
    }

    @Override
    public Set<Course> findCoursesByTeacherId(Long id) {
        String query = "SELECT * FROM course WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return new LinkedHashSet<>(buildCourseListWithoutTeacher(resultSet));
        } catch (SQLException e) {
            LOGGER.warn("CourseDao.Select all courses exception {}", e);
            throw new QueryException(e);
        }
    }

    @Override
    public Course findById(Long courseId) {
        String query = "SELECT * FROM course JOIN user ON course.user_id = user.user_id WHERE course_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return buildCourse(resultSet);
        } catch (SQLException e) {
            LOGGER.warn("CourseDao.Find course by id exception {}", e);
            throw new QueryException(e);
        }
    }

    private Course buildCourse(ResultSet resultSet) throws SQLException {
        Course course = null;
        while (resultSet.next()) {
            course = new CourseBuilder()
                    .withId(resultSet.getLong("course_id"))
                    .withTitle(resultSet.getString("title"))
                    .withCreateDate(resultSet.getTimestamp("create_date"))
                    .withStatus(resultSet.getBoolean("course_status"))
                    .withTeacher(new UserBuilder()
                            .withId(resultSet.getLong("user_id"))
                            .withUsername(resultSet.getString("username"))
                            .withBirthDate(resultSet.getTimestamp("birthDate"))
                            .build())
                    .build();
        }
        return course;
    }

    @Override
    public Set<Course> readAll() {
        String query = "SELECT * FROM course JOIN user ON course.user_id = user.user_id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return buildCourseList(resultSet);
        } catch (SQLException e) {
            LOGGER.warn("CourseDao.Select all courses exception {}", e);
            throw new QueryException(e);
        }
    }

    private Set<Course> buildCourseList(ResultSet resultSet) throws SQLException {
        Set<Course> courses = new LinkedHashSet<>();
        while (resultSet.next()) {
            Course course = new CourseBuilder()
                    .withId(resultSet.getLong("course_id"))
                    .withTitle(resultSet.getString("title"))
                    .withCreateDate(resultSet.getTimestamp("create_date"))
                    .withStatus(resultSet.getBoolean("course_status"))
                    .withTeacher(new UserBuilder()
                            .withId(resultSet.getLong("user_id"))
                            .withUsername(resultSet.getString("username"))
                            .withBirthDate(resultSet.getTimestamp("birthDate"))
                            .build())
                    .build();
            courses.add(course);
        }
        return courses;
    }

    private List<Course> buildCourseListWithoutTeacher(ResultSet resultSet) throws SQLException {
        List<Course> courses = new ArrayList<>();
        while (resultSet.next()) {
            Course course = new CourseBuilder()
                    .withId(resultSet.getLong("course_id"))
                    .withTitle(resultSet.getString("title"))
                    .withCreateDate(resultSet.getTimestamp("create_date"))
                    .withStatus(resultSet.getBoolean("course_status"))
                    .build();
            courses.add(course);
        }
        return courses;
    }
}
