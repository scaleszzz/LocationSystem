package com.example.locationsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "t_user_location_access")
public class UserLocationAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Location location;

    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;
}
