package com.example.locationsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_locations")
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<UserLocationAccess> userAccesses;
}
