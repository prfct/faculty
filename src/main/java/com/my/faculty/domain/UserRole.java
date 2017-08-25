package com.my.faculty.domain;

/**
 * @author Oleksii Petrokhalko.
 */
public enum UserRole {
    ADMIN, DEFAULT, STUDENT_MANAGER, TEACHER_MANAGER;

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
