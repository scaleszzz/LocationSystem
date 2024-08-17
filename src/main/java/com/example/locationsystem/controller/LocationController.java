package com.example.locationsystem.controller;

import com.example.locationsystem.dto.LocationDTO;
import com.example.locationsystem.mapper.UserMapper;
import com.example.locationsystem.service.LocationService;
import com.example.locationsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<LocationDTO> createLocation(@RequestBody LocationDTO locationDto) {
        LocationDTO createdLocation = locationService.createLocation(locationDto);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        List<LocationDTO> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LocationDTO>> getLocationsByUserId(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(userDto -> new ResponseEntity<>(locationService.getLocationsByUserId(
                        UserMapper.INSTANCE.toEntity(userDto)), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
