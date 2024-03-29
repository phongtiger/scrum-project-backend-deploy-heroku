package com.codegym.lastproject.controller;

import com.codegym.lastproject.model.*;
import com.codegym.lastproject.model.util.RoleName;
import com.codegym.lastproject.model.util.StatusHouse;
import com.codegym.lastproject.model.util.StatusOrder;
import com.codegym.lastproject.security.service.UserDetailsServiceImpl;
import com.codegym.lastproject.service.*;
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

    @Autowired
    private OrderStatusService orderStatusService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/")
    public ResponseEntity<List<OrderHouse>> getListOrderHouseByUser() {
        User originUser = userDetailsService.getCurrentUser();

        List<OrderHouse> orderHouses = orderHouseService.findByTenantId(originUser.getId());
        if (orderHouses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderHouses, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('HOST') or hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<OrderHouse>> getListProcessingOrderHouseByHouseId(@PathVariable("id") Long id) {
        List<OrderHouse> orderHouses = orderHouseService.findProcessingOrderByHouseId(id);
        if (orderHouses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderHouses, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('HOST') or hasRole('ADMIN')")
    @GetMapping(value = "/all/{id}")
    public ResponseEntity<List<OrderHouse>> getListAllOrderHouseByHouseId(@PathVariable("id") Long id) {
        List<OrderHouse> orderHouses = orderHouseService.findByHouseId(id);
        if (orderHouses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderHouses, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/create")
    public ResponseEntity<Void> createOrderHouse(@RequestBody OrderHouse orderHouse) {
        OrderHouse originOrderHouse = new OrderHouse();
        Date checkin = orderHouse.getCheckin();
        Date checkout = orderHouse.getCheckout();
        Date orderDate = new Date(System.currentTimeMillis());

        if (checkin.getTime() > checkout.getTime() || checkin.getTime() < orderDate.getTime()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User originUser = userDetailsService.getCurrentUser();
        originOrderHouse.setTenant(originUser);

        if (orderHouse.getHouse() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Long id = orderHouse.getHouse().getId();
        House originHouse = houseService.findById(id);
        originOrderHouse.setHouse(originHouse);

        //check xem co trang thai available hay khong
        HouseStatus houseStatus = new HouseStatus(originHouse, checkin, checkout, statusService.findByStatus(StatusHouse.BOOKED));
        HouseStatus houseStatus1 = houseStatusService.findHouseStatusAvailable(checkin, checkout, id);

        if (houseStatus1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

        originOrderHouse.setCheckin(checkin);
        originOrderHouse.setCheckout(checkout);
        originOrderHouse.setOrderDate(orderDate);
        originOrderHouse.setOrderStatus(orderStatusService.findByStatus(StatusOrder.PROCESSING));
        orderHouseService.saveOrder(originOrderHouse);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteOrderHouse(@PathVariable("id") Long id) {
        User originUser = userDetailsService.getCurrentUser();
        OrderHouse orderHouse = orderHouseService.findById(id);

        Role role = originUser.getRole().iterator().next();

        if (role.getName() == RoleName.ROLE_USER) {
            if (orderHouse.getTenant() != originUser) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            boolean isConformity = houseService.isConformity(orderHouse);
            if (isConformity) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        Date beginDate = orderHouse.getCheckin();
        Date endDate = orderHouse.getCheckout();
        Long houseId = orderHouse.getHouse().getId();

        Date newBeginDate, newEndDate;

        HouseStatus houseStatus1 = houseStatusService.findHouseStatusBooked(beginDate, endDate, houseId);
        houseStatusService.deleteById(houseStatus1.getId());

        HouseStatus houseStatus2 = houseStatusService.findHouseStatusEnd(new Date(beginDate.getTime() - 86400000L), houseId);
        if (houseStatus2 != null) {
            newBeginDate = houseStatus2.getBeginDate();
            houseStatusService.deleteById(houseStatus2.getId());
        } else {
            newBeginDate = beginDate;
        }

        HouseStatus houseStatus3 = houseStatusService.findHouseStatusBegin(new Date(endDate.getTime() + 86400000L), houseId);
        if (houseStatus3 != null) {
            newEndDate = houseStatus3.getEndDate();
            houseStatusService.deleteById(houseStatus3.getId());
        } else {
            newEndDate = endDate;
        }

        HouseStatus houseStatus = new HouseStatus(orderHouse.getHouse(), newBeginDate, newEndDate, statusService.findByStatus(StatusHouse.AVAILABLE));

        houseStatusService.save(houseStatus);

        orderHouse.setOrderStatus(orderStatusService.findByStatus(StatusOrder.CANCELED));
        orderHouseService.saveOrder(orderHouse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
