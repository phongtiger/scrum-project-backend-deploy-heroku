package com.codegym.lastproject;

import com.codegym.lastproject.service.RoleService;
import com.codegym.lastproject.service.UserService;
import com.codegym.lastproject.service.impl.RoleServiceImpl;
import com.codegym.lastproject.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LastProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LastProjectApplication.class, args);
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public RoleService roleService() {
        return new RoleServiceImpl();
    }
}
