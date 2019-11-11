package com.codegym.lastproject.controller;

import com.codegym.lastproject.model.Role;
import com.codegym.lastproject.model.User;
import com.codegym.lastproject.service.RoleService;
import com.codegym.lastproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Controller
public class UserRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("role")
    public Iterable<Role> allRole() {
        return roleService.findAll();
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = (List<User>) userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/list/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        System.out.println("Fetching User with id: " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> createUser(@RequestBody User user) {
//        Iterable<User> originUser = userService.search(user.getEmail());
//        if (!(originUser == null)) {
//            return new ResponseEntity<>(HttpStatus.);
//        }
        System.out.println("Creating User " + user.getName());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User originUser = userService.findById(id);

        if (originUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        originUser.setEmail(user.getEmail());
        originUser.setPassword(user.getPassword());

        if (user.getRole() != null) {
            Role originRole = roleService.findById(user.getRole().getId());
            if (originRole == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            originUser.setRole(originRole);
        }

        userService.save(originUser);
        return new ResponseEntity<>(originUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> apiDeleteUser(@PathVariable("id") Long id) {
        User target = userService.findById(id);

        if (target == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.remove(target.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
