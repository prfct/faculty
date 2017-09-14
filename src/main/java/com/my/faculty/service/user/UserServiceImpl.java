package com.my.faculty.service.user;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Role;
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

import java.time.LocalDateTime;
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
    public User createUser(String username, String email, String password, LocalDateTime birthDate)
            throws UserExistException {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            connection.beginTransaction();
            if (daoFactory.getAuthDao(connection).findByEmailAndPassword(email, password) != null) {
                LOGGER.warn("Service.User with email '{}' and username '{}' already created", email, username);
                throw new UserExistException();
            }
            User user = new User(username, birthDate);
            User createdUser = daoFactory.getUserDao(connection).create(user);
            Auth auth = new Auth(email, createdUser);
            Auth createdAuth = daoFactory.getAuthDao(connection).createAuth(auth, password);
            Long studentRoleId = daoFactory.getUserRoleDao(connection).getDefaultRole();
            daoFactory.getUserRoleDao(connection).addRole(auth.getId(), studentRoleId);
            LOGGER.info("Service.User with id '{}', username '{}', email '{}' successful created",
                    createdUser.getId(), createdUser.getUsername(), createdAuth.getEmail());
            connection.commitTransaction();
            return createdUser;
        }
    }

    @Override
    public Auth login(String email, String password) throws UserNotExistException {
        try (AbstractConnection connection = connectionPool.getConnection()) {
            Auth userAuth = daoFactory.getAuthDao(connection).findByEmailAndPassword(email, password);
            if (userAuth != null) {
                Set<Role> roles = daoFactory.getUserRoleDao(connection).getRolesByUser(userAuth.getId());
                if (roles != null && !roles.isEmpty()) {
                    userAuth.setUserRole(roles);
                }
                LOGGER.info("Service.User found with id '{}'", userAuth.getUser().getId());
                return userAuth;
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
