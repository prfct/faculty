package com.my.faculty.domain;

/**
 * @author Oleksii Petrokhalko.
 */
public enum UserRole {
    ADMIN("admin"), STUDENT("student"), TEACHER("teacher");

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
