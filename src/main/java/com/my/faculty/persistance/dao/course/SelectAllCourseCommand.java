package com.my.faculty.persistance.dao.course;

import com.my.faculty.domain.Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SelectAllCourseCommand {
    private static final String QUERY = "SELECT * FROM course";

    protected String getQuery() {
        return QUERY;
    }

    protected void setupStatement(PreparedStatement preparedStatement) throws SQLException {

    }

    protected List<Course> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Course> courseList = new ArrayList<>();
        while (resultSet.next()) {
            Course course = new Course();
            course.setId(resultSet.getLong("course_id"));
            course.setTitle(resultSet.getString("title"));
            Timestamp timestamp = resultSet.getTimestamp("date");
            course.setCreateDate(timestamp.toLocalDateTime());
            courseList.add(course);
        }
        return courseList;
    }
}
