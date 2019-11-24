package com.codegym.lastproject.controller;

import com.codegym.lastproject.model.House;
import com.codegym.lastproject.model.Role;
import com.codegym.lastproject.model.User;
import com.codegym.lastproject.security.service.UserDetailsServiceImpl;
import com.codegym.lastproject.service.CategoryService;
import com.codegym.lastproject.service.HouseService;
import com.codegym.lastproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/host")
public class HostRestController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/createHouse")
    public ResponseEntity<House> createHouse(@RequestBody House house) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/editHouse")
    public ResponseEntity<House> editHouse(@RequestBody House house) {

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
