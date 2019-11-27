package com.codegym.lastproject.repository;

import com.codegym.lastproject.model.util.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import com.codegym.lastproject.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);
}
