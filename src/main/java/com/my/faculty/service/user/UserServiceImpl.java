package com.my.faculty.service.user;

import com.my.faculty.context.ApplicationContext;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.persistance.dao.user.UserDao;
import com.my.faculty.persistance.db.ConnectionManager;
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
    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private UserDao userDao = ApplicationContext.getDaoContext().getUserDao();

    @Override
    public User createUser(User user) throws UserExistException {
        if (userDao.findByEmail(user.getEmail()) != null) {
            LOGGER.warn("Service.User with email '{}' and username '{}' already created", user.getEmail(),
                    user.getUsername());
            throw new UserExistException("This user already created");
        }
        user.setUserRole(UserRole.DEFAULT);
        User createdUser = userDao.create(user);
        LOGGER.info("Service.User with id '{}', username '{}' and email '{}' successful created",
                createdUser.getId(), createdUser.getUsername(), createdUser.getEmail());
        connectionManager.commit();
        return createdUser;
    }

    @Override
    public User loginUser(String email, String password) throws UserNotExistException {
        User user = userDao.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            LOGGER.info("Service.User found with id '{}'", user.getId());
            connectionManager.commit();
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
