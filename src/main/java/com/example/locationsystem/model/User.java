package com.example.locationsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email",unique = true,nullable = false)
    private String email;

}
