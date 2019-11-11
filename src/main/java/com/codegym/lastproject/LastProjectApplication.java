package com.codegym.lastproject;

import com.codegym.lastproject.formatter.RoleFormatter;
import com.codegym.lastproject.service.RoleService;
import com.codegym.lastproject.service.UserService;
import com.codegym.lastproject.service.impl.RoleServiceImpl;
import com.codegym.lastproject.service.impl.UserServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    @Configuration
    @EnableSpringDataWebSupport
    class WebConfig implements WebMvcConfigurer, ApplicationContextAware {

        private ApplicationContext appContext;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            appContext = applicationContext;
        }

        @Override
        public void addFormatters(FormatterRegistry registry) {
            RoleService roleService = appContext.getBean(RoleService.class);
            Formatter roleFormatter = new RoleFormatter(roleService);
            registry.addFormatter(roleFormatter);
        }
    }


}
