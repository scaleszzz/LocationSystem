package com.example.locationsystem.dto;

import com.example.locationsystem.model.AccessLevel;
import lombok.Data;

@Data
public class LocationShareRequest {
    private Long userId;
    private AccessLevel accessLevel;
}