package com.codegym.lastproject.service.impl;

import com.codegym.lastproject.model.Role;
import com.codegym.lastproject.repository.RoleRepository;
import com.codegym.lastproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.get();
    }


    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }
}
