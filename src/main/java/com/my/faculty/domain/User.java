package com.my.faculty.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Oleksii Petrokhalko.
 */
public class User {
    private Long id;
    private String username;
    private LocalDate birthDate;
    private Auth auth;

    public User() {
    }

    public User(String username, LocalDate birthDate) {
        this.username = username;
        this.birthDate = birthDate;
    }

    public User(Long id, String username, LocalDate birthDate) {
        this.id = id;
        this.username = username;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
