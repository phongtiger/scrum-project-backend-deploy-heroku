package com.codegym.lastproject.model;

import com.codegym.lastproject.model.util.StatusOrder;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private StatusOrder name;

    public OrderStatus() {
    }

    public OrderStatus(StatusOrder name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusOrder getName() {
        return name;
    }

    public void setName(StatusOrder name) {
        this.name = name;
    }
}
