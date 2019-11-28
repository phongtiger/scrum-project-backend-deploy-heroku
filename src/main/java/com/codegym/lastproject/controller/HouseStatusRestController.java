package com.codegym.lastproject.controller;

import com.codegym.lastproject.model.House;
import com.codegym.lastproject.model.HouseStatus;
import com.codegym.lastproject.model.Status;
import com.codegym.lastproject.model.util.StatusHouse;
import com.codegym.lastproject.security.service.UserDetailsServiceImpl;
import com.codegym.lastproject.service.HouseService;
import com.codegym.lastproject.service.HouseStatusService;
import com.codegym.lastproject.service.StatusService;
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
    private StatusService statusService;

    @Autowired
    private HouseStatusService houseStatusService;

    @Autowired
    private HouseService houseService;

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
        if (houseStatus.getHouse() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            Long id = houseStatus.getHouse().getId();
            House originHouse = houseService.findById(id);
            originHouseStatus.setHouse(originHouse);
        }

        originHouseStatus.setBeginDate(houseStatus.getBeginDate());
        originHouseStatus.setEndDate(houseStatus.getEndDate());

        if (houseStatus.getStatus() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            StatusHouse status = houseStatus.getStatus().getName();
            Status originStatus = statusService.findByStatus(status);
            originHouseStatus.setStatus(originStatus);
        }

        houseStatusService.save(originHouseStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
