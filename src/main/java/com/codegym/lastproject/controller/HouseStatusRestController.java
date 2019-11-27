package com.codegym.lastproject.controller;

import com.codegym.lastproject.model.HouseStatus;
import com.codegym.lastproject.model.util.StatusHouse;
import com.codegym.lastproject.security.service.UserDetailsServiceImpl;
import com.codegym.lastproject.service.HouseService;
import com.codegym.lastproject.service.HouseStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/status")
public class HouseStatusRestController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private HouseStatusService houseStatusService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HouseStatus>> getListHouse(@PathVariable("id") Long id) {
        List<HouseStatus> houseStatuses = houseStatusService.findAllByHouseId(id);
        if (houseStatuses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(houseStatuses, HttpStatus.OK);
    }

    @PostMapping(value = "/set", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> setHouseStatus(@RequestBody HouseStatus houseStatus) {
        HouseStatus originHouseStatus = new HouseStatus();
        originHouseStatus.setId(houseStatus.getHouse().getId());
        originHouseStatus.setBeginDate(houseStatus.getBeginDate());
        originHouseStatus.setEndDate(houseStatus.getEndDate());

        houseStatusService.save(originHouseStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
