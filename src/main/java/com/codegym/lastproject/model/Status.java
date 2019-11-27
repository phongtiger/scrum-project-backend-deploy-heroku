package com.codegym.lastproject.model;

import com.codegym.lastproject.model.util.StatusHouse;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private StatusHouse statusHouse;

    public Status() {
    }

    public Status(StatusHouse statusHouse) {
        this.statusHouse = statusHouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusHouse getStatusHouse() {
        return statusHouse;
    }

    public void setStatusHouse(StatusHouse statusHouse) {
        this.statusHouse = statusHouse;
    }
}
