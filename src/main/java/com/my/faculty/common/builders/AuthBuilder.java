package com.my.faculty.common.builders;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.User;
import com.my.faculty.domain.UserRole;

public class AuthBuilder implements Builder<Auth> {
    private Auth auth;

    public AuthBuilder() {
        auth = new Auth();
    }

    public AuthBuilder withId(Long id) {
        auth.setId(id);
        return this;
    }

    public AuthBuilder withEmail(String email) {
        auth.setEmail(email);
        return this;
    }

    public AuthBuilder withPassword(String password) {
        auth.setPassword(password);
        return this;
    }

    public AuthBuilder withRole(UserRole userRole) {
        auth.setUserRole(userRole);
        return this;
    }

    public AuthBuilder withUser(User user) {
        auth.setUser(user);
        return this;
    }

    @Override
    public Auth build() {
        return auth;
    }
}
