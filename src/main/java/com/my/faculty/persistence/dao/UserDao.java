package com.my.faculty.persistence.dao;

import com.my.faculty.domain.User;

import java.util.Collection;
import java.util.Set;

/**
 * @author Oleksii Petrokhalko.
 */
public interface UserDao {
    User create(User user);

    Set<User> findAll(int offset, int limit);

    Set<User> findAll();

    User findById(Long id);

    void update(User user);

    int findUserCount();

}
