package com.my.faculty.persistence.dao;

import com.my.faculty.domain.Auth;

public interface AuthDao {
    Auth createAuth(Auth auth);

    Auth findByEmail(Auth auth);

    void update(Auth auth);

    Auth findByUserId(Long id);

    Auth findByEmailAndPassword(String email, String password);
}
