package com.codegym.lastproject.controller;

import com.codegym.lastproject.model.*;
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
        User originUser = userDetailsService.getCurrentUser();
        House originHouse = new House();
        originHouse.setUser(originUser);

        if (house.getCategory() != null) {
            String category = house.getCategory().getName();
            Category originCategory = categoryService.findByName(category);
            originHouse.setCategory(originCategory);
        }

        if (house.getImageUrls() != null) {
            originHouse.setImageUrls(house.getImageUrls());
        }

        originHouse.setAddress(house.getAddress());
        originHouse.setArea(house.getArea());
        originHouse.setBathroomNumber(house.getBathroomNumber());
        originHouse.setBedroomNumber(house.getBedroomNumber());
        originHouse.setHouseName(house.getHouseName());
        originHouse.setPrice(house.getPrice());
        originHouse.setStatus(Status.AVAILABLE);
        originHouse.setOrderHouses(null);

        houseService.save(originHouse);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/editHouse/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<House> editHouse(@PathVariable("id") Long id, @RequestBody House house) {
        User originUser = userDetailsService.getCurrentUser();
        List<House> houses = houseService.findByHostId(originUser.getId());
        House originHouse = houseService.findById(id);
        boolean isHost = houses.contains(originHouse);
        if (originHouse == null || !isHost) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        house.setId(id);
        houseService.save(house);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/deleteHouse/{id}")
    public ResponseEntity<Void> deleteHouse(@PathVariable("id") Long id) {
        User originUser = userDetailsService.getCurrentUser();
        List<House> houses = houseService.findByHostId(originUser.getId());
        House house = houseService.findById(id);
        boolean isHost = houses.contains(house);
        if (house == null || !isHost) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        houseService.deleteHouse(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
