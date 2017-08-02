package com.my.faculty.persistance.dao.user;

import com.my.faculty.domain.User;
import com.my.faculty.persistance.dao.AbstractDao;

import java.util.Collection;

/**
 * @author Oleksii Petrokhalko.
 */
public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public User create(User user) {
        return executeDataCommand(new InsertUserCommand(user));
    }

    @Override
    public User findByEmail(String email) {
        return executeDataCommand(new SelectUserByIdCommand(email));
    }

    @Override
    public Collection<User> read(int offset, int limit) {
        return null;
    }

    @Override
    public Integer findUserCount() {
        return null;
    }
}
