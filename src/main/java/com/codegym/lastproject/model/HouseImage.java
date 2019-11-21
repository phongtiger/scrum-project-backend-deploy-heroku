package com.codegym.lastproject.model;

import javax.persistence.*;

@Entity
@Table(name = "image_house")
public class HouseImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    public HouseImage() {
    }

    public HouseImage(String imageUrl, House house) {
        this.imageUrl = imageUrl;
        this.house = house;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
