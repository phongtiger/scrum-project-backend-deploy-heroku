package com.codegym.lastproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_house")
public class OrderHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "house_id")
    @JsonBackReference
    private House house;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private User tenant;

    private Date checkin;
    private Date checkout;
    private Long numberGuest;
    private Long cost;
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    public OrderHouse() {
    }

    public OrderHouse(House house, User tenant, Date checkin, Date checkout, Long numberGuest, Long cost, Date orderDate, StatusOrder statusOrder) {
        this.house = house;
        this.tenant = tenant;
        this.checkin = checkin;
        this.checkout = checkout;
        this.numberGuest = numberGuest;
        this.cost = cost;
        this.orderDate = orderDate;
        this.statusOrder = statusOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public Long getNumberGuest() {
        return numberGuest;
    }

    public void setNumberGuest(Long numberGuest) {
        this.numberGuest = numberGuest;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }
}
