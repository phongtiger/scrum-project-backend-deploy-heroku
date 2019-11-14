package com.codegym.lastproject.controller;

import com.codegym.lastproject.model.Role;
import com.codegym.lastproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Controller
public class RoleRestController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public ResponseEntity<List<Role>> listAllRoles() {
        List<Role> roles = roleService.findAll();
        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

//    @GetMapping(value = "/role/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Role> getRole(@PathVariable("id") Long id) {
//        System.out.println("Fetching Role with id: " + id);
//        Role role = roleService.findById(id);
//        if (role == null) {
//            System.out.println("Role with id " + id + " not found");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(role, HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<Void> createRole(@RequestBody Role role) {
//        System.out.println("Creating Role " + role.getName());
//        roleService.save(role);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PutMapping(value = "/role/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Role> updateRole(@PathVariable("id") Long id, @RequestBody Role role) {
//        Role originRole = roleService.findById(id);
//
//        if (originRole == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        originRole.setName(role.getName());
//
//        roleService.save(originRole);
//        return new ResponseEntity<>(originRole, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/role/delete/{id}")
//    @ResponseBody
//    public ResponseEntity<Void> apiDeleteRole(@PathVariable("id") Long id) {
//        Role target = roleService.findById(id);
//
//        if (target == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        roleService.remove(target.getId());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
