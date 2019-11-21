package com.codegym.lastproject.service;

import com.codegym.lastproject.model.Role;
import com.codegym.lastproject.model.RoleName;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findByName(RoleName roleName);

    void save(Role role);
}
