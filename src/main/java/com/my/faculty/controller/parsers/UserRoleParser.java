package com.my.faculty.controller.parsers;

import com.my.faculty.domain.UserRole;

/**
 * Created by Stein on 24.04.17.
 */
public class UserRoleParser extends Parser<UserRole> {

    @Override
    public UserRole parse(String key, String[] params) {
        UserRole userRole = null;
        if (params != null && params.length != 0) {
            userRole = Enum.valueOf(UserRole.class, params[0]);
        }
        return userRole;
    }
}
