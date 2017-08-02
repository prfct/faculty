package com.my.faculty.persistance.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Oleksii Petrokhalko.
 */
public abstract class UpdateQuery<T> implements DataCommand<T> {
    private Connection connection = ConnectionManager.getInstance().getOrCreateConnection();

    @Override
    public T execute() {
        String sql = getQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setupStatement(preparedStatement);
            preparedStatement.executeUpdate();
            return returnUpdatedObject();
        } catch (SQLException e) {
//            LOGGER.error(e.getMessage(), e);
            throw new QueryException(e);
        }
    }

    protected abstract String getQuery();

    protected abstract void setupStatement(PreparedStatement preparedStatement) throws SQLException;

    protected abstract T returnUpdatedObject() throws SQLException;
}
