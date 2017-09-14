package com.my.faculty.persistance.dao;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;

import java.util.Collection;
import java.util.Set;

/**
 * @author Oleksii Petrokhalko.
 */
public interface UserDao {
    User create(User user);

    Collection<User> read(int offset, int limit);

    Integer findUserCount();

    Set<User> findAll();

    User findById(Long id);
}
