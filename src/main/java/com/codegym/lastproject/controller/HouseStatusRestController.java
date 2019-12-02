package com.codegym.lastproject.controller;

import com.codegym.lastproject.model.House;
import com.codegym.lastproject.model.HouseStatus;
import com.codegym.lastproject.model.Status;
import com.codegym.lastproject.model.util.StatusHouse;
import com.codegym.lastproject.service.HouseService;
import com.codegym.lastproject.service.HouseStatusService;
import com.codegym.lastproject.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/status")
public class HouseStatusRestController {
    @Autowired
    private StatusService statusService;

    @Autowired
    private HouseStatusService houseStatusService;

    @Autowired
    private HouseService houseService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<HouseStatus>> getListHouse(@PathVariable("id") Long id) {
        List<HouseStatus> houseStatuses = houseStatusService.findAllByHouseId(id);
        if (houseStatuses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(houseStatuses, HttpStatus.OK);
    }

    @PostMapping(value = "/set")
    public ResponseEntity<Void> setHouseStatus(@RequestBody HouseStatus houseStatus) {
        Long id = houseStatus.getHouse().getId();
        Date beginDate = houseStatus.getBeginDate();
        Date endDate = houseStatus.getEndDate();
        House house = houseStatus.getHouse();

        Status status = statusService.findByStatus(houseStatus.getStatus().getName());
        Status availableStatus = statusService.findByStatus(StatusHouse.AVAILABLE);

        if (beginDate.getTime() > endDate.getTime()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HouseStatus houseStatus1 = houseStatusService.findHouseStatusAvailable(beginDate, endDate, id);

        if (houseStatus1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Date beginDate1 = houseStatus1.getBeginDate();
        Date endDate1 = houseStatus1.getEndDate();

        if ((beginDate == beginDate1)
                && (endDate == endDate1)) {
            houseStatus1.setStatus(status);
            houseStatusService.save(houseStatus1);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        boolean isBeginDayEqual = !beginDate.toString().equals(beginDate1.toString());
        if (isBeginDayEqual) {
            HouseStatus houseStatus2 = new HouseStatus(house, beginDate1, new Date(beginDate.getTime() - 86400000L), availableStatus);
            houseStatusService.save(houseStatus2);
        }

        HouseStatus originHouseStatus = new HouseStatus(house, beginDate, endDate, status);
        houseStatusService.save(originHouseStatus);

        boolean isEndDayEqual = !endDate.toString().equals(endDate1.toString());
        if (isEndDayEqual) {
            HouseStatus houseStatus3 = new HouseStatus(house, new Date(endDate.getTime() + 86400000L), endDate1, availableStatus);
            houseStatusService.save(houseStatus3);
        }

        houseStatusService.deleteById(houseStatus1.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
