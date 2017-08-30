package com.my.faculty.persistance.dao.user;

import com.my.faculty.common.Key;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.persistance.db.QueryException;

import java.sql.*;
import java.util.Collection;

/**
 * @author Oleksii Petrokhalko.
 */
public class UserDaoImpl implements UserDao {
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO user(username, email, password, userRole) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUserRole().toString());

            int status = preparedStatement.executeUpdate();
            if (status != Key.ONE) {
                throw new SQLException("Creating failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long generatedId = generatedKeys.getLong(Key.ONE);
                    user.setId(generatedId);
                    return user;
                } else {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new QueryException(e);
        }
    }

    @Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        try {
            return executeAndGetUser(query, email);
        } catch (SQLException e) {
            throw new QueryException(e);
        }
    }

    @Override
    public Collection<User> read(int offset, int limit) {
        return null;
    }

    @Override
    public Integer findUserCount() {
        return null;
    }

    private User executeAndGetUser(String query, String email) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
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
}
