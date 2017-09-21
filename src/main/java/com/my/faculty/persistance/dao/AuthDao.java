package com.my.faculty.persistance.dao;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;

import java.util.Set;

public interface AuthDao {
    Auth createAuth(Auth auth);

    Auth findByEmail(Auth auth);

    void update(Auth auth);

    Auth findByUserId(Long id);

    Auth findByEmailAndPassword(String email, String password);
}
