package com.my.faculty.domain;

/**
 * @author Oleksii Petrokhalko.
 */
public class Student {
    private Long id;
    private User user;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
