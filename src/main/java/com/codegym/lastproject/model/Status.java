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
    private StatusHouse status;

    public Status() {
    }

    public Status(StatusHouse status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusHouse getStatus() {
        return status;
    }

    public void setStatus(StatusHouse status) {
        this.status = status;
    }
}
