package com.codegym.lastproject.controller;

import com.codegym.lastproject.model.Role;
import com.codegym.lastproject.model.User;
import com.codegym.lastproject.security.service.UserDetailsServiceImpl;
import com.codegym.lastproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class UserRestController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ResponseEntity<User> getCurrentUser() {
        User user = userDetailsService.getCurrentUser();
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/profile/edit")
    public ResponseEntity<User> updateProfile(@RequestBody User user) {
        User originUser = userDetailsService.getCurrentUser();
        if (user != null) {
            originUser.setName(user.getName());
            originUser.setPhone(user.getPhone());
            originUser.setAddress(user.getAddress());
            originUser.setAvatar(user.getAvatar());

            userService.saveUser(originUser);
            return new ResponseEntity<>(originUser, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/profile/editPassword")
    public ResponseEntity<User> updatePassword(@RequestBody User user) {
        User originUser = userDetailsService.getCurrentUser();
        if (user != null) {
            originUser.setPassword(encoder.encode(user.getPassword()));

            userService.saveUser(originUser);
            return new ResponseEntity<>(originUser, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/role")
    public ResponseEntity<Role> getRole() {
        User originUser = userDetailsService.getCurrentUser();
        Role role = originUser.getRole().iterator().next();

        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
