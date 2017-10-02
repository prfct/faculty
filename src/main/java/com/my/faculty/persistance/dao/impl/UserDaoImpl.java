package com.my.faculty.persistance.dao.impl;

import com.my.faculty.common.Key;
import com.my.faculty.common.builders.UserBuilder;
import com.my.faculty.domain.Auth;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.persistance.dao.UserDao;
import com.my.faculty.persistance.db.QueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Oleksii Petrokhalko.
 */
public class UserDaoImpl implements UserDao {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO user(username, birthDate) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(user.getBirthDate().atStartOfDay()));
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long generatedId = generatedKeys.getLong(Key.ONE);
                user.setId(generatedId);
            }
            return user;
        } catch (SQLException e) {
            LOGGER.warn("UserDao.Create user exception {}", e);
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
    public void update(User user) {
        String query = "UPDATE user SET username = ?, birthDate = ? WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(user.getBirthDate().atStartOfDay()));
            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("UserDao.Update user exception {}", e);
            throw new QueryException(e);
        }
    }

    @Override
    public User findById(Long id) {
        String query = "SELECT * FROM user WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return buildUser(resultSet);
        } catch (SQLException e) {
            LOGGER.warn("UserDao.Find user exception {}", e);
            throw new QueryException(e);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = null;
        while (resultSet.next()) {
            user = new UserBuilder()
                    .withId(resultSet.getLong("user_id"))
                    .withUsername(resultSet.getString("username"))
                    .withBirthDate(resultSet.getTimestamp("birthDate")
                            .toLocalDateTime()
                            .toLocalDate())
                    .build();
        }
        return user;
    }

    @Override
    public Set<User> findAll() {
        String query = "SELECT * FROM user JOIN auth ON auth.user_id = user.user_id";
        try {
            return executeAndGetUsersSet(query);
        } catch (SQLException e) {
            LOGGER.warn("UserDao.Select all users exception {}", e);
            throw new QueryException(e);
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
                Timestamp timestamp = resultSet.getTimestamp("birthDate");
                user.setBirthDate(timestamp.toLocalDateTime().toLocalDate());
                Auth auth = new Auth();
                auth.setId(resultSet.getLong("auth_id"));
                auth.setEmail(resultSet.getString("email"));
                auth.setUserRole(UserRole.valueOf(resultSet.getString("userRole")));
                user.setAuth(auth);
                users.add(user);
            }
            return users;
        }
    }
}
