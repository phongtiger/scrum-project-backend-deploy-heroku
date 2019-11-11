package com.codegym.lastproject.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}
