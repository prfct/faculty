package com.my.faculty.persistance.dao;


import com.my.faculty.domain.Auth;
import com.my.faculty.domain.Role;

import java.util.Set;

public interface RoleDao {
    boolean addRole(Long authId, Long roleId);

    Long getDefaultRole();

    Set<Role> getRolesByUser(Long authId);
}
