package com.my.faculty.persistance.db;


import com.my.faculty.common.Key;

import java.sql.*;

/**
 * @author Oleksii Petrokhalko.
 */
public abstract class InsertQuery<T> implements DataCommand<T> {
    private Connection connection = ConnectionManager.getInstance().getOrCreateConnection();

    @Override
    public T execute() {
        String sql = getQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setupStatement(preparedStatement);
            int status = preparedStatement.executeUpdate();
            if (status != Key.ONE) {
                throw new SQLException("Creating failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long generatedId = generatedKeys.getLong(Key.ONE);
                    return extractInserted(generatedId);
                } else {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new QueryException(e);
        }
    }

    protected abstract String getQuery();

    protected abstract void setupStatement(PreparedStatement preparedStatement) throws SQLException;

    protected abstract T extractInserted(Long id) throws SQLException;
}
