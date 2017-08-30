package com.my.faculty.service.user;

import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.persistance.dao.DaoFactory;
import com.my.faculty.persistance.dao.user.UserDao;
import com.my.faculty.persistance.db.ConnectionPool;
import com.my.faculty.persistance.db.MySqlConnectionPool;
import com.my.faculty.service.exception.UserExistException;
import com.my.faculty.service.exception.UserNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public class UserServiceImpl implements UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private DaoFactory df = DaoFactory.getMySqlDaoFactory();
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
        if (df.getUserDao(connectionPool.getConnection()).findByEmail(user.getEmail()) != null) {
            LOGGER.warn("Service.User with email '{}' and username '{}' already created", user.getEmail(),
                    user.getUsername());
            throw new UserExistException("This user already created");
        }
        user.setUserRole(UserRole.DEFAULT);
        User createdUser = df.getUserDao(connectionPool.getConnection()).create(user);
        LOGGER.info("Service.User with id '{}', username '{}' and email '{}' successful created",
                createdUser.getId(), createdUser.getUsername(), createdUser.getEmail());
        return createdUser;
    }

    @Override
    public User loginUser(String email, String password) throws UserNotExistException {
        User user = df.getUserDao(connectionPool.getConnection()).findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            LOGGER.info("Service.User found with id '{}'", user.getId());
            return user;
        }
        LOGGER.warn("Service.User with email '{}' not exist or incorrect password", email);
        throw new UserNotExistException("This user doesn't exist");
    }

    @Override
    public List<User> showUserList() {
        return null;
    }
}
