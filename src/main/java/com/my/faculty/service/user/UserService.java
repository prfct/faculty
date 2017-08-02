package com.my.faculty.service.user;

import com.my.faculty.domain.User;
import com.my.faculty.service.exception.UserExistException;
import com.my.faculty.service.exception.UserNotExistException;

import java.util.List;

/**
 * @author Oleksii Petrokhalko.
 */
public interface UserService {
    User createUser(User user) throws UserExistException;
    User loginUser(String email, String password) throws UserNotExistException;
    List<User> showUserList();
}
