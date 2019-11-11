package com.codegym.lastproject.service;

import com.codegym.lastproject.model.Role;

public interface RoleService {
    Iterable<Role> findAll();

    Role findById(Long id);

    void save(Role role);

    void remove(Long id);
}
