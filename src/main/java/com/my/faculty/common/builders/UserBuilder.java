package com.my.faculty.common.builders;

import com.my.faculty.domain.Auth;
import com.my.faculty.domain.User;

import java.sql.Timestamp;
import java.time.LocalDate;

public class UserBuilder implements Builder<User> {
    private User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder withId(Long id) {
        user.setId(id);
        return this;
    }

    public UserBuilder withUsername(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder withBirthDate(LocalDate birthDate) {
        user.setBirthDate(birthDate);
        return this;
    }

    public UserBuilder withBirthDate(Timestamp birthDate) {
        user.setBirthDate(birthDate.toLocalDateTime().toLocalDate());
        return this;
    }

    public UserBuilder withAuth(Auth auth) {
        user.setAuth(auth);
        return this;
    }

    @Override
    public User build() {
        return user;
    }
}
