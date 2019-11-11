package com.codegym.lastproject.formatter;

import com.codegym.lastproject.model.Role;
import com.codegym.lastproject.service.RoleService;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoleFormatter implements Formatter<Role> {
    private RoleService roleService;

    public RoleFormatter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role parse(String text, Locale locale) throws ParseException {
        return roleService.findById(Long.valueOf(text));
    }

    @Override
    public String print(Role object, Locale locale) {
        return object.toString();
    }
}
