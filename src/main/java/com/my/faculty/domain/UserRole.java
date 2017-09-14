package com.my.faculty.domain;

/**
 * @author Oleksii Petrokhalko.
 */
public enum UserRole {
    ADMIN("admin"), DEFAULT("default"), STUDENT("student"), TEACHER("teacher");

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public static UserRole fromString(String string) {
        UserRole result = null;
        for (UserRole role : values()) {
            if (role.toString().equalsIgnoreCase(string)) {
                result = role;
                break;
            }
        }
        return result;
    }

}
