package com.codegym.lastproject.config;

import com.codegym.lastproject.model.Role;
import com.codegym.lastproject.model.RoleName;
import com.codegym.lastproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (RoleName roleName : RoleName.values()) {
            if (roleRepository.findByName(roleName) == null) {
                roleRepository.save(new Role(roleName));
            }
        }
    }
}
