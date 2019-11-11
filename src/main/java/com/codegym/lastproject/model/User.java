package com.codegym.lastproject.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String password;

    private String name;
    private String phone;
    private String address;

    private String avatar;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
