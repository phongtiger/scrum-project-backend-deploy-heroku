package com.codegym.lastproject.service;

import com.codegym.lastproject.model.Role;
import com.codegym.lastproject.model.RoleName;

public interface RoleService {
    Role findByName(RoleName roleName);

    void save(Role role);
}
