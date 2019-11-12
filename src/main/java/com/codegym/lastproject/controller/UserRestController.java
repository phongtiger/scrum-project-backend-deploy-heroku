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

    @GetMapping("/user")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = (List<User>) userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        System.out.println("Fetching User with id: " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/user/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        User originUser = userService.search(user.getEmail());
        if (originUser != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("Creating User " + user.getName());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @DeleteMapping("/user/delete/{id}")
//    @ResponseBody
//    public ResponseEntity<Void> apiDeleteUser(@PathVariable("id") Long id) {
//        User target = userService.findById(id);
//
//        if (target == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        userService.remove(target.getId());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PutMapping(value = "/user/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User originUser = userService.findById(id);

        if (originUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        originUser.setName(user.getName());
        originUser.setPhone(user.getPhone());
        originUser.setAddress(user.getAddress());
        originUser.setAvatar(user.getAvatar());

        userService.save(originUser);
        return new ResponseEntity<>(originUser, HttpStatus.OK);
    }

    @PutMapping(value = "/user/editPassword/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> editPassword(@PathVariable("id") Long id, @RequestBody User user) {
        User originUser = userService.findById(id);
        if (originUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        originUser.setPassword(user.getPassword());

        userService.save(originUser);
        return new ResponseEntity<>(originUser, HttpStatus.OK);
    }
}
