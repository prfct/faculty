package com.my.faculty.persistance.dao.user;

import com.my.faculty.domain.User;
import com.my.faculty.persistance.db.InsertQuery;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Oleksii Petrokhalko.
 */
public class InsertUserCommand extends InsertQuery<User> {
    private static final String QUERY = "INSERT INTO user(username, email, password, userRole) VALUES (?, ?, ?, ?)";
    private User user;

    public InsertUserCommand(User user) {
        this.user = user;
    }

    @Override
    protected String getQuery() {
        return QUERY;
    }

    @Override
    protected void setupStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getUserRole().toString());
    }

    @Override
    protected User extractInserted(Long generatedId) throws SQLException {
        user.setId(generatedId);
        return user;
    }
}
