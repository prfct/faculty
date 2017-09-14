package com.my.faculty.domain;

import java.util.Set;

public class Auth {
    private Long id;
    private String email;
    private User user;
    private Set<Role> userRole;

    public Auth() {
    }

    public Auth(String email, User user) {
        this.email = email;
        this.user = user;
    }

    public Auth(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<Role> userRole) {
        this.userRole = userRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
