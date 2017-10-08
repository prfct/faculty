package com.my.faculty.persistence.dao.impl;

import com.my.faculty.common.Key;
import com.my.faculty.common.builders.AuthBuilder;
import com.my.faculty.common.builders.UserBuilder;
import com.my.faculty.domain.Auth;
import com.my.faculty.domain.UserRole;
import com.my.faculty.persistence.dao.AuthDao;
import com.my.faculty.persistence.dao.exception.QueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class AuthDaoImpl implements AuthDao {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private Connection connection;

    public AuthDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Auth createAuth(Auth auth) {
        String query = "INSERT INTO auth (email, password, userRole, user_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, auth.getEmail());
            preparedStatement.setString(2, auth.getPassword());
            preparedStatement.setString(3, auth.getUserRole().toString());
            preparedStatement.setLong(4, auth.getUser().getId());
            int status = preparedStatement.executeUpdate();
            if (status != Key.ONE) {
                throw new SQLException("Creating failed, no rows affected.");
            }
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                auth.setId(generatedKeys.getLong(Key.ONE));
            }
            return auth;
        } catch (SQLException e) {
            LOGGER.warn("AuthDao.Create auth exception {}", e);
            throw new QueryException(e);
        }
    }

    @Override
    public void update(Auth auth) {
        String query = "UPDATE auth SET email = ?, password = ?,  userRole = ? WHERE auth_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, auth.getEmail());
            preparedStatement.setString(2, auth.getPassword());
            preparedStatement.setString(3, auth.getUserRole().toString());
            preparedStatement.setLong(4, auth.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("AuthDao.Update auth exception {}", e);
            throw new QueryException(e);
        }
    }

    @Override
    public Auth findByUserId(Long id) {
        String query = "SELECT * FROM auth WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return buildAuthWithoutUser(resultSet);
        } catch (SQLException e) {
            LOGGER.warn("AuthDao.Find auth by userId exception {}", e);
            throw new QueryException(e);
        }
    }

    @Override
    public Auth findByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM user JOIN auth ON auth.user_id = user.user_id WHERE email = ? AND password = ? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return buildAuthWithUser(resultSet);
        } catch (SQLException e) {
            LOGGER.warn("AuthDao.Find auth by email and password exception {}", e);
            throw new QueryException(e);
        }
    }

    private Auth buildAuthWithoutUser(ResultSet resultSet) throws SQLException {
        Auth auth = null;
        while (resultSet.next()) {
            auth = new AuthBuilder()
                    .withId(resultSet.getLong("auth_id"))
                    .withEmail(resultSet.getString("email"))
                    .withPassword(resultSet.getString("password"))
                    .withRole(UserRole.valueOf(resultSet.getString("userRole")))
                    .build();
        }
        return auth;
    }

    @Override
    public Auth findByEmail(Auth auth) {
        String query = "SELECT * FROM user JOIN auth ON auth.user_id = user.user_id WHERE email = ? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, auth.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            return buildAuthWithUser(resultSet);
        } catch (SQLException e) {
            LOGGER.warn("AuthDao.Find auth by email exception {}", e);
            throw new QueryException(e);
        }
    }

    private Auth buildAuthWithUser(ResultSet resultSet) throws SQLException {
        Auth auth = null;
        while (resultSet.next()) {
            auth = new AuthBuilder()
                    .withId(resultSet.getLong("auth_id"))
                    .withEmail(resultSet.getString("email"))
                    .withPassword(resultSet.getString("password"))
                    .withRole(UserRole.valueOf(resultSet.getString("userRole")))
                    .withUser(new UserBuilder()
                            .withId(resultSet.getLong("user_id"))
                            .withUsername(resultSet.getString("username"))
                            .withBirthDate(resultSet.getTimestamp("birthDate")
                                    .toLocalDateTime()
                                    .toLocalDate())
                            .build())

                    .build();
        }
        return auth;
    }

}
