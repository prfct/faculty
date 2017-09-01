package com.my.faculty.persistance.dao.user;

import com.my.faculty.domain.User;

import java.util.Collection;
import java.util.Set;

/**
 * @author Oleksii Petrokhalko.
 */
public interface UserDao {
    User create(User user);

    User findByEmail(String email);

    Collection<User> read(int offset, int limit);

    Integer findUserCount();

    Set<User> findAll();

    User findById(Long id);
}
