package com.my.faculty.service.user;

import com.my.faculty.domain.Teacher;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.persistance.dao.DaoFactory;
import com.my.faculty.persistance.db.AbstractConnection;
import com.my.faculty.persistance.db.ConnectionPool;
import com.my.faculty.persistance.db.MySqlConnectionPool;
import com.my.faculty.service.exception.UserExistException;
import com.my.faculty.service.exception.UserNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author Oleksii Petrokhalko.
 */
public class UserServiceImpl implements UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
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
    public User createUser(String username, String email, String password) throws UserExistException {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            if (daoFactory.getUserDao(connection).findByEmail(email) != null) {
                LOGGER.warn("Service.User with email '{}' and username '{}' already created", email, username);
                throw new UserExistException();
            }
            User user = new User(username, email, password);
            user.setUserRole(UserRole.DEFAULT);
            User createdUser = daoFactory.getUserDao(connection).create(user);
            LOGGER.info("Service.User with id '{}', username '{}' and email '{}' successful created",
                    createdUser.getId(), createdUser.getUsername(), createdUser.getEmail());
            return createdUser;
        }
    }

    @Override
    public User loginUser(String email, String password) throws UserNotExistException {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            User user = daoFactory.getUserDao(connection).findByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                LOGGER.info("Service.User found with id '{}'", user.getId());
                return user;
            }
            LOGGER.warn("Service.User with email '{}' not exist or incorrect password", email);
            throw new UserNotExistException();
        }
    }

    @Override
    public Set<User> showUserList() {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            Set<User> users = daoFactory.getUserDao(connection).findAll();
            return users;
        }
    }

    @Override
    public User read(Long id) {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            User user = daoFactory.getUserDao(connection).findById(id);
            if (user != null) {
                LOGGER.info("Service.User found with id '{}'", user.getId());
                return user;
            }
            LOGGER.warn("Service.User with id '{}' not exist ", id);
            return user;
        }
    }
}
