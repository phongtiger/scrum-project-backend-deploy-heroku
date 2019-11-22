package com.codegym.lastproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String houseName;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    private String address;
    private Long bedroomNumber;
    private Long bathroomNumber;
    private Long area;
    private Long price;

    private List<String> imageUrls;

    @OneToMany(targetEntity = OrderHouse.class)
    @JsonManagedReference
    private List<OrderHouse> orderHouses;

    @Enumerated(EnumType.STRING)
    private Status status;

    public House() {
    }

    public House(String houseName, User user, Category category, String address, Long bedroomNumber, Long bathroomNumber, Long area, Long price, List<String> imageUrls, List<OrderHouse> orderHouses, Status status) {
        this.houseName = houseName;
        this.user = user;
        this.category = category;
        this.address = address;
        this.bedroomNumber = bedroomNumber;
        this.bathroomNumber = bathroomNumber;
        this.area = area;
        this.price = price;
        this.imageUrls = imageUrls;
        this.orderHouses = orderHouses;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getBedroomNumber() {
        return bedroomNumber;
    }

    public void setBedroomNumber(Long bedroomNumber) {
        this.bedroomNumber = bedroomNumber;
    }

    public Long getBathroomNumber() {
        return bathroomNumber;
    }

    public void setBathroomNumber(Long bathroomNumber) {
        this.bathroomNumber = bathroomNumber;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<OrderHouse> getOrderHouses() {
        return orderHouses;
    }

    public void setOrderHouses(List<OrderHouse> orderHouses) {
        this.orderHouses = orderHouses;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
