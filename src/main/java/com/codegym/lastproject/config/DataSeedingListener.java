package com.codegym.lastproject.config;

import com.codegym.lastproject.model.Category;
import com.codegym.lastproject.model.Role;
import com.codegym.lastproject.model.RoleName;
import com.codegym.lastproject.service.CategoryService;
import com.codegym.lastproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private RoleService roleService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (RoleName roleName : RoleName.values()) {
            if (roleService.findByName(roleName) == null) {
                roleService.save(new Role(roleName));
            }
        }

        String[] categories = new String[]{"House", "Villa", "Resort", "Hotel"};
        for (String category : categories) {
            if (categoryService.findByName(category) == null) {
                categoryService.save(new Category(category));
            }
        }
    }
}
