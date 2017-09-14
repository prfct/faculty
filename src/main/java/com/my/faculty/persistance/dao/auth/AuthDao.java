package com.my.faculty.persistance.dao.auth;

import com.my.faculty.domain.Auth;

public interface AuthDao {
    Auth createAuth(Auth auth, String password);

    Auth findByEmailAndPassword(String email, String password);
}
