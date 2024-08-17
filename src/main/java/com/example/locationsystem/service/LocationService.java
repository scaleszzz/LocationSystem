package com.example.locationsystem.service;

import com.example.locationsystem.dto.LocationDTO;
import com.example.locationsystem.dto.UserLocationAccessDTO;
import com.example.locationsystem.mapper.LocationMapper;
import com.example.locationsystem.mapper.UserLocationAccessMapper;
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

    public LocationDTO createLocation(LocationDTO locationDto) {
        Location location = LocationMapper.INSTANCE.toEntity(locationDto);
        return LocationMapper.INSTANCE.toDto(locationRepository.save(location));
    }

    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return LocationMapper.INSTANCE.toDtoList(locations);
    }

    public Optional<LocationDTO> getLocationById(long id) {
        return locationRepository.findById(id)
                .map(LocationMapper.INSTANCE::toDto);
    }

    public List<LocationDTO> getLocationsByUserId(User owner) {
        List<Location> locations = locationRepository.findByOwner(owner);
        return LocationMapper.INSTANCE.toDtoList(locations);
    }

    public UserLocationAccessDTO shareLocationWithUser(LocationDTO locationDto, User user, AccessLevel accessLevel) {
        Location location = LocationMapper.INSTANCE.toEntity(locationDto);
        UserLocationAccess userLocationAccess = new UserLocationAccess();
        userLocationAccess.setLocation(location);
        userLocationAccess.setUser(user);
        userLocationAccess.setAccessLevel(accessLevel);
        return UserLocationAccessMapper.INSTANCE.toDto(userLocationAccessRepository.save(userLocationAccess));
    }

    public List<UserLocationAccessDTO> getUserAccessesByLocation(Long locationId) {
        List<UserLocationAccess> accesses = userLocationAccessRepository.findByLocationId(locationId);
        return UserLocationAccessMapper.INSTANCE.toDtoList(accesses);
    }

    public void deleteLocationById(Long id){
        locationRepository.deleteById(id);
    }

    public boolean removeUserAccessFromLocation(Long locationId, Long userId) {
        List<UserLocationAccess> accesses = userLocationAccessRepository.findByLocationId(locationId);
        if(!accesses.isEmpty()){
            userLocationAccessRepository.deleteAll(accesses);
            return true;
        }
        return false;
    }
}
