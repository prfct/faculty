package com.my.faculty.service.impl;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.persistance.dao.DaoFactory;
import com.my.faculty.persistance.db.AbstractConnection;
import com.my.faculty.persistance.db.ConnectionPool;
import com.my.faculty.persistance.db.MySqlConnectionPool;
import com.my.faculty.service.UserService;
import com.my.faculty.service.exception.UserExistException;

import java.util.Set;

/**
 * @author Oleksii Petrokhalko.
 */
public class UserServiceImpl implements UserService {
    private DaoFactory daoFactory = DaoFactory.getMySqlDaoFactory();
    private ConnectionPool connectionPool = MySqlConnectionPool.getInstance();

    private UserServiceImpl() {
    }

    private static class InstanceHolder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public User createUser(User user) throws UserExistException {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            connection.beginTransaction();
             Auth userAuth = user.getAuth();
            if (daoFactory.getAuthDao(connection).findByEmail(userAuth) != null) {
                throw new UserExistException();
            }
            User createdUser = daoFactory.getUserDao(connection).create(user);
            userAuth.setUser(createdUser);
            daoFactory.getAuthDao(connection).createAuth(userAuth);
            connection.commitTransaction();
            return createdUser;
        }
    }

    @Override
    public Auth login(String email, String password) {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            return daoFactory.getAuthDao(connection).findByEmailAndPassword(email, password);
        }
    }

    @Override
    public Set<User> showUserList() {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            return daoFactory.getUserDao(connection).findAll();
        }
    }

    @Override
    public User read(Long id) {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            return daoFactory.getUserDao(connection).findById(id);
        }
    }

    @Override
    public void update(User user, UserRole userRole) {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            connection.beginTransaction();
            daoFactory.getUserDao(connection).update(user);
            Auth currentAuth = daoFactory.getAuthDao(connection).findByUserId(user.getId());
            currentAuth.setUserRole(userRole);
            daoFactory.getAuthDao(connection).update(currentAuth);
            connection.commitTransaction();
        }
    }
}
