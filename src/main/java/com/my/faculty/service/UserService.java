package com.my.faculty.service;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;
import com.my.faculty.service.exception.UserExistException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Oleksii Petrokhalko.
 */
public interface UserService {
    User createUser(User user) throws UserExistException;

    Auth login(String email, String password);

    Set<User> showUserList();

    User read(Long id);

    void update(User user, UserRole userRole);
}
