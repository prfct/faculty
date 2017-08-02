package com.my.faculty.domain;

/**
 * @author Oleksii Petrokhalko.
 */
public class Teacher {
    private Long id;
    private User user;

    public Teacher() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
