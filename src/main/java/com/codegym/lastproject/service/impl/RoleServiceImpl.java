package com.codegym.lastproject.service.impl;

import com.codegym.lastproject.model.Role;
import com.codegym.lastproject.model.util.RoleName;
import com.codegym.lastproject.repository.RoleRepository;
import com.codegym.lastproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
