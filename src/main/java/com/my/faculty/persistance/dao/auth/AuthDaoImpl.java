package com.my.faculty.persistance.dao.auth;

import com.my.faculty.common.Key;
import com.my.faculty.domain.Auth;
import com.my.faculty.domain.User;
import com.my.faculty.persistance.db.QueryException;

import java.sql.*;

public class AuthDaoImpl implements AuthDao {
    private Connection connection;

    public AuthDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Auth createAuth(Auth auth, String password) {
        String query = "INSERT INTO auth (email, password, user_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, auth.getEmail());
            preparedStatement.setString(2, password);
            preparedStatement.setLong(3, auth.getUser().getId());
            int status = preparedStatement.executeUpdate();
            if (status != Key.ONE) {
                throw new SQLException("Creating failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    auth.setId(generatedKeys.getLong(Key.ONE));
                    return auth;
                } else {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new QueryException(e);
        }
    }

    @Override
    public Auth findByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM user JOIN auth ON auth.user_id = user.user_id WHERE email = ? AND password = ?";
        try {
            return executeAndGetUserAuth(query, email, password);
        } catch (SQLException e) {
            throw new QueryException(e);
        }
    }

    private Auth executeAndGetUserAuth(String query, String email, String password) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            Auth userAuth = null;
            while (resultSet.next()) {
                userAuth = new Auth();
                userAuth.setId(resultSet.getLong("auth_id"));
                userAuth.setEmail(resultSet.getString("email"));
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setUsername(resultSet.getString("username"));
                Timestamp timestamp = resultSet.getTimestamp("birthDate");
                user.setBirthDate(timestamp.toLocalDateTime());
                userAuth.setUser(user);
            }
            return userAuth;
        }
    }
}
