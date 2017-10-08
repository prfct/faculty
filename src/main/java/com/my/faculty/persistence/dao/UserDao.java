package com.my.faculty.persistence.dao;

import com.my.faculty.domain.User;

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

    void update(User user);
}
