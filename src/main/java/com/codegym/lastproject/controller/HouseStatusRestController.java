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

import java.sql.Date;
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

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createHouseStatus(@RequestBody HouseStatus houseStatus) {
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

    @PostMapping(value = "/set", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> setHouseStatus(@RequestBody HouseStatus houseStatus) {
        Long id = houseStatus.getHouse().getId();
        Date beginDate = houseStatus.getBeginDate();
        Date endDate = houseStatus.getEndDate();

        HouseStatus houseStatus1 = houseStatusService.findHouseStatusAvailable(beginDate, endDate, id);

        if (houseStatus1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        if ((houseStatus.getBeginDate() == houseStatus1.getBeginDate())
                && (houseStatus.getEndDate() == houseStatus1.getEndDate())) {
            houseStatus1.setStatus(statusService.findByStatus(houseStatus.getStatus().getName()));
            houseStatusService.save(houseStatus1);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        boolean isBeginDayEqual = !houseStatus.getBeginDate().toString().equals(houseStatus1.getBeginDate().toString());
        System.out.println(isBeginDayEqual);
        if (isBeginDayEqual) {
            HouseStatus houseStatus2 = new HouseStatus(houseStatus.getHouse(), houseStatus1.getBeginDate(), new Date(houseStatus.getBeginDate().getTime() - 86400000L), statusService.findByStatus(StatusHouse.AVAILABLE));
            houseStatusService.save(houseStatus2);
        }

        HouseStatus originHouseStatus = new HouseStatus(houseStatus.getHouse(), houseStatus.getBeginDate(), houseStatus.getEndDate(), statusService.findByStatus(houseStatus.getStatus().getName()));
        houseStatusService.save(originHouseStatus);

        boolean isEndDayEqual = !houseStatus.getEndDate().toString().equals(houseStatus1.getEndDate().toString());
        System.out.println(isEndDayEqual);
        if (isEndDayEqual) {
            HouseStatus houseStatus3 = new HouseStatus(houseStatus.getHouse(), new Date(houseStatus.getEndDate().getTime() + 86400000L), houseStatus1.getEndDate(), statusService.findByStatus(StatusHouse.AVAILABLE));
            houseStatusService.save(houseStatus3);
        }

        houseStatusService.deleteById(houseStatus1.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
