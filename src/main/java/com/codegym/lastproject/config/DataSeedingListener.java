package com.codegym.lastproject.config;

import com.codegym.lastproject.model.Category;
import com.codegym.lastproject.model.Role;
import com.codegym.lastproject.model.Status;
import com.codegym.lastproject.model.util.CategoryName;
import com.codegym.lastproject.model.util.RoleName;
import com.codegym.lastproject.model.util.StatusHouse;
import com.codegym.lastproject.service.CategoryService;
import com.codegym.lastproject.service.RoleService;
import com.codegym.lastproject.service.StatusService;
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

    @Autowired
    private StatusService statusService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (RoleName roleName : RoleName.values()) {
            if (roleService.findByName(roleName) == null) {
                roleService.save(new Role(roleName));
            }
        }

        for (CategoryName category : CategoryName.values()) {
            if (categoryService.findByName(category) == null) {
                categoryService.save(new Category(category));
            }
        }

        for (StatusHouse statusHouse : StatusHouse.values()) {
            if (statusService.findByStatus(statusHouse) == null) {
                statusService.save(new Status(statusHouse));
            }
        }
    }
}
