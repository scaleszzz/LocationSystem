package com.example.locationsystem.dto;

import com.example.locationsystem.model.AccessLevel;
import lombok.Data;

@Data
public class UserLocationAccessDTO {

    private Long id;
    private UserDTO user;
    private LocationDTO location;
    private AccessLevel accessLevel;
}
