package com.my.faculty.service.impl;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.persistence.dao.DaoFactory;
import com.my.faculty.persistence.dao.DaoConnection;
import com.my.faculty.service.UserService;
import com.my.faculty.service.exception.UserExistException;

import java.util.Set;

/**
 * @author Oleksii Petrokhalko.
 */
public class UserServiceImpl implements UserService {
    private DaoFactory daoFactory;

    private UserServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static class InstanceHolder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl(DaoFactory.getInstance());
    }

    public static UserService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public User createUser(User user) throws UserExistException {
        try (DaoConnection connection = daoFactory.getDaoConnection()) {
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
        try (DaoConnection connection = daoFactory.getDaoConnection()) {
            return daoFactory.getAuthDao(connection).findByEmailAndPassword(email, password);
        }
    }

    @Override
    public Set<User> showUserList() {
        try (DaoConnection connection = daoFactory.getDaoConnection()) {
            return daoFactory.getUserDao(connection).findAll();
        }
    }

    @Override
    public User read(Long id) {
        try (DaoConnection connection = daoFactory.getDaoConnection()) {
            return daoFactory.getUserDao(connection).findById(id);
        }
    }

    @Override
    public void update(User user, UserRole userRole) {
        try (DaoConnection connection = daoFactory.getDaoConnection()) {
            connection.beginTransaction();
            daoFactory.getUserDao(connection).update(user);
            Auth currentAuth = daoFactory.getAuthDao(connection).findByUserId(user.getId());
            currentAuth.setUserRole(userRole);
            daoFactory.getAuthDao(connection).update(currentAuth);
            connection.commitTransaction();
        }
    }
}
