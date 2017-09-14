package com.my.faculty.persistance.dao.impl;

import com.my.faculty.common.Key;
import com.my.faculty.domain.User;
import com.my.faculty.persistance.dao.UserDao;
import com.my.faculty.persistance.db.QueryException;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
        String query = "INSERT INTO user(username, birthDate) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(user.getBirthDate()));
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
    public Collection<User> read(int offset, int limit) {
        return null;
    }

    @Override
    public Integer findUserCount() {
        return null;
    }

    @Override
    public Set<User> findAll() {
        String query = "SELECT * FROM user";
        try {
            return executeAndGetUsersSet(query);
        } catch (SQLException e) {
            throw new QueryException(e);
        }
    }

    @Override
    public User findById(Long id) {
        String query = "SELECT * FROM user WHERE user_id = ?";
        try {
            return executeAndGetUserById(query, id);
        } catch (SQLException e) {
            throw new QueryException(e);
        }
    }

    private User executeAndGetUserById(String query, Long id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setUsername(resultSet.getString("username"));
//                user.setEmail(resultSet.getString("email"));
//                user.setPassword(resultSet.getString("password"));
//                user.setUserRole(UserRole.fromString(resultSet.getString("userRole")));
            }
            return user;
        }
    }

    private Set<User> executeAndGetUsersSet(String query) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<User> users = new HashSet<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setUsername(resultSet.getString("username"));
//                user.setEmail(resultSet.getString("email"));
//                user.setPassword(resultSet.getString("password"));
//                user.setUserRole(UserRole.fromString(resultSet.getString("userRole")));
                users.add(user);
            }
            return users;
        }
    }
}
