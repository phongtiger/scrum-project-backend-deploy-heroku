package com.codegym.lastproject.repository;

import org.springframework.data.repository.CrudRepository;
import com.codegym.lastproject.model.Role;


public interface RoleRepository extends CrudRepository<Role, Long> {
}
