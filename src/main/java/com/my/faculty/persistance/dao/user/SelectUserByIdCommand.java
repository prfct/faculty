package com.my.faculty.persistance.dao.user;

import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.persistance.db.SelectQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Oleksii Petrokhalko.
 */
public class SelectUserByIdCommand extends SelectQuery<User> {
    private static final String QUERY = "SELECT * FROM user WHERE email = ?";
    private String email;

    public SelectUserByIdCommand(String email) {
        this.email = email;
    }

    @Override
    protected String getQuery() {
        return QUERY;
    }

    @Override
    protected void setupStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, email);
    }

    @Override
    protected User parseResultSet(ResultSet resultSet) throws SQLException {
        User user = null;
        while (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setUserRole(UserRole.fromString(resultSet.getString("userRole")));
        }
        return user;
    }
}
