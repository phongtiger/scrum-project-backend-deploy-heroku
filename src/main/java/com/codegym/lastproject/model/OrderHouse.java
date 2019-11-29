package com.codegym.lastproject.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "order_house")
public class OrderHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private User tenant;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date checkin;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date checkout;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date orderDate;

    public OrderHouse() {
    }

    public OrderHouse(House house, User tenant, Date checkin, Date checkout, Date orderDate) {
        this.house = house;
        this.tenant = tenant;
        this.checkin = checkin;
        this.checkout = checkout;
        this.orderDate = orderDate;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
