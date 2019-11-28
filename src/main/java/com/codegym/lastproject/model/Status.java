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
    private StatusHouse name;

    public Status() {
    }

    public Status(StatusHouse name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusHouse getName() {
        return name;
    }

    public void setName(StatusHouse name) {
        this.name = name;
    }
}
