package com.codegym.lastproject.controller;

import com.codegym.lastproject.model.House;
import com.codegym.lastproject.model.HouseStatus;
import com.codegym.lastproject.model.OrderHouse;
import com.codegym.lastproject.model.User;
import com.codegym.lastproject.model.util.StatusHouse;
import com.codegym.lastproject.security.service.UserDetailsServiceImpl;
import com.codegym.lastproject.service.HouseService;
import com.codegym.lastproject.service.HouseStatusService;
import com.codegym.lastproject.service.OrderHouseService;
import com.codegym.lastproject.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/order")
public class OrderRestController {
    @Autowired
    private OrderHouseService orderHouseService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private HouseStatusService houseStatusService;

    @Autowired
    private HouseService houseService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/")
    public ResponseEntity<List<OrderHouse>> getListOrderHouseByUser() {
        User originUser = userDetailsService.getCurrentUser();

        List<OrderHouse> orderHouses = orderHouseService.findByTenantId(originUser.getId());
        if (orderHouses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderHouses, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('HOST') or hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<OrderHouse>> getListOrderHouseByHouseId(@PathVariable("id") Long id) {
        List<OrderHouse> orderHouses = orderHouseService.findByHouseId(id);
        if (orderHouses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderHouses, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/create")
    public ResponseEntity<Void> createOrderHouse(@RequestBody OrderHouse orderHouse) {
        OrderHouse originOrderHouse = new OrderHouse();
        Date checkin = orderHouse.getCheckin();
        Date checkout = orderHouse.getCheckout();

        if (checkin.getTime() > checkout.getTime()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        User originUser = userDetailsService.getCurrentUser();
        originOrderHouse.setTenant(originUser);

        if (orderHouse.getHouse() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Long id = orderHouse.getHouse().getId();
        House originHouse = houseService.findById(id);
        originOrderHouse.setHouse(originHouse);

        HouseStatus houseStatus = new HouseStatus(originHouse, checkin, checkout, statusService.findByStatus(StatusHouse.BOOKED));
        HouseStatus houseStatus1 = houseStatusService.findHouseStatusAvailable(checkin, checkout, id);

        if (houseStatus1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Date beginDate1 = houseStatus1.getBeginDate();
        Date endDate1 = houseStatus1.getEndDate();

        if ((checkin == beginDate1)
                && (checkout == endDate1)) {
            houseStatus1.setStatus(statusService.findByStatus(houseStatus.getStatus().getName()));
            houseStatusService.save(houseStatus1);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        boolean isBeginDayEqual = !checkin.toString().equals(beginDate1.toString());
        System.out.println(isBeginDayEqual);
        if (isBeginDayEqual) {
            HouseStatus houseStatus2 = new HouseStatus(houseStatus.getHouse(), beginDate1, new Date(checkin.getTime() - 86400000L), statusService.findByStatus(StatusHouse.AVAILABLE));
            houseStatusService.save(houseStatus2);
        }

        HouseStatus originHouseStatus = new HouseStatus(houseStatus.getHouse(), checkin, checkout, statusService.findByStatus(houseStatus.getStatus().getName()));
        houseStatusService.save(originHouseStatus);

        boolean isEndDayEqual = !checkout.toString().equals(endDate1.toString());
        System.out.println(isEndDayEqual);
        if (isEndDayEqual) {
            HouseStatus houseStatus3 = new HouseStatus(houseStatus.getHouse(), new Date(checkout.getTime() + 86400000L), endDate1, statusService.findByStatus(StatusHouse.AVAILABLE));
            houseStatusService.save(houseStatus3);
        }

        houseStatusService.deleteById(houseStatus1.getId());

        Date orderDate = new Date(System.currentTimeMillis());

        originOrderHouse.setCheckin(checkin);
        originOrderHouse.setCheckout(checkout);
        originOrderHouse.setOrderDate(orderDate);
        orderHouseService.saveOrder(originOrderHouse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
