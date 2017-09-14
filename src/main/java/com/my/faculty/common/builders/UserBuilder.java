package com.my.faculty.common.builders;

import com.my.faculty.domain.User;

import java.time.LocalDateTime;

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

    public UserBuilder withBirthDate(LocalDateTime birthDate) {
        user.setBirthDate(birthDate);
        return this;
    }

    @Override
    public User build() {
        return user;
    }
}
