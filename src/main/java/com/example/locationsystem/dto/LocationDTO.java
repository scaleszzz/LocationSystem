package com.example.locationsystem.dto;

import lombok.Data;

@Data
public class LocationDTO {
    private Long id;
    private String name;
    private String address;
    private Long ownerId;
}
