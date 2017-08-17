package com.my.faculty.persistance.dao.teacher;

import com.my.faculty.domain.Teacher;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.persistance.db.SelectQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTeacherByCourseIdCommand extends SelectQuery<Teacher> {
    private static final String QUERY1 = "SELECT * FROM teacher t JOIN user u ON t.user_id = u.user_id" +
            " WHERE t.teacher_id = (SELECT teacher_id FROM course WHERE course_id = ?)";
    private Long course_id;

    public SelectTeacherByCourseIdCommand(Long course_id) {
        this.course_id = course_id;
    }

    @Override
    protected String getQuery() {
        return QUERY1;
    }

    @Override
    protected void setupStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, course_id);
    }

    @Override
    protected Teacher parseResultSet(ResultSet resultSet) throws SQLException {
        Teacher teacher = null;
        while (resultSet.next()) {
            teacher = new Teacher();
            teacher.setId(resultSet.getLong("teacher_id"));
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setUserRole(UserRole.fromString(resultSet.getString("userRole")));
            teacher.setUser(user);
        }
        return teacher;
    }
}
