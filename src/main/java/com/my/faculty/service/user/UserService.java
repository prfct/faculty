package com.my.faculty.service.user;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.User;
import com.my.faculty.service.exception.UserExistException;
import com.my.faculty.service.exception.UserNotExistException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author Oleksii Petrokhalko.
 */
public interface UserService {
    User createUser(String username, String email, String password, LocalDateTime birthDate) throws UserExistException;

    Auth login(String email, String password) throws UserNotExistException;

    Set<User> showUserList();

    User read(Long id);

}
