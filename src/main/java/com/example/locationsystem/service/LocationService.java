package com.example.locationsystem.service;

import com.example.locationsystem.model.AccessLevel;
import com.example.locationsystem.model.Location;
import com.example.locationsystem.model.User;
import com.example.locationsystem.model.UserLocationAccess;
import com.example.locationsystem.repository.LocationRepository;
import com.example.locationsystem.repository.UserLocationAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final UserLocationAccessRepository userLocationAccessRepository;

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(long id) {
        return locationRepository.findById(id);
    }

    public List<Location> getLocationsByUserId(User owner) {
        return locationRepository.findByOwner(owner);
    }

    public UserLocationAccess shareLocationWithUser(Location location, User user, AccessLevel accessLevel) {
        UserLocationAccess userLocationAccess = new UserLocationAccess();
        userLocationAccess.setLocation(location);
        userLocationAccess.setUser(user);
        userLocationAccess.setAccessLevel(accessLevel);
        return userLocationAccessRepository.save(userLocationAccess);
    }

    public List<UserLocationAccess> getUserAccessesByLocation(Long locationId) {
        return userLocationAccessRepository.findByLocationId(locationId);
    }
}
