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
    private LocalDateTime birthDate;

    public User() {
    }

    public User(String username, LocalDateTime birthDate) {
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

//    public LocalDate getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }
}
