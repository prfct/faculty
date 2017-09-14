package com.my.faculty.persistance.dao.impl;


import com.my.faculty.domain.Role;
import com.my.faculty.domain.UserRole;
import com.my.faculty.persistance.dao.RoleDao;
import com.my.faculty.persistance.db.QueryException;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class RoleDaoImpl implements RoleDao {
    private Connection connection;

    public RoleDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addRole(Long authId, Long roleId) {
        String query = "INSERT INTO auth_role(role_id, auth_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, roleId);
            preparedStatement.setLong(2, authId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new QueryException(e);
        }
    }

    @Override
    public Long getDefaultRole() {
        String query = "SELECT * FROM role WHERE role = 'STUDENT'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Long roleId = null;
            while (resultSet.next()) {
                roleId = resultSet.getLong("role_id");
            }
            return roleId;
        } catch (SQLException e) {
            throw new QueryException(e);
        }
    }

    @Override
    public Set<Role> getRolesByUser(Long authId) {
        String query = "SELECT role.role_id, role FROM auth_role " +
                "JOIN role ON role.role_id = auth_role.role_id " +
                "JOIN auth ON auth.auth_id = auth_role.auth_id " +
                "WHERE auth_role.auth_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, authId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<Role> roles = new HashSet<>();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong("role_id"));
                role.setUserRole(UserRole.valueOf(resultSet.getString("role")));
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            throw new QueryException(e);
        }
    }
}
