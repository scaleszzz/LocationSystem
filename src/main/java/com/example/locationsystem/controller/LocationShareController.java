package com.example.locationsystem.controller;

import com.example.locationsystem.dto.LocationShareRequest;
import com.example.locationsystem.dto.UserLocationAccessDTO;
import com.example.locationsystem.mapper.UserMapper;
import com.example.locationsystem.model.AccessLevel;
import com.example.locationsystem.service.LocationService;
import com.example.locationsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location-access")
@RequiredArgsConstructor
public class LocationShareController {

    private final LocationService locationService;
    private final UserService userService;

    @PostMapping("/{locationId}/share")
    public ResponseEntity<UserLocationAccessDTO> shareLocation(
            @PathVariable Long locationId,
            @RequestBody LocationShareRequest request) {
        return locationService.getLocationById(locationId)
                .flatMap(locationDto -> userService.getUserById(request.getUserId())
                        .map(userDto -> {
                            UserLocationAccessDTO access = locationService.shareLocationWithUser(locationDto,
                                    UserMapper.INSTANCE.toEntity(userDto), request.getAccessLevel());
                            return new ResponseEntity<>(access, HttpStatus.OK);
                        }))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{locationId}/users")
    public ResponseEntity<List<UserLocationAccessDTO>> getUserAccessesByLocation(@PathVariable Long locationId) {
        List<UserLocationAccessDTO> accesses = locationService.getUserAccessesByLocation(locationId);
        return new ResponseEntity<>(accesses, HttpStatus.OK);
    }

    @DeleteMapping("/{locationId}/users/{userId}")
    public ResponseEntity<Void> removeUserAccessFromLocation(@PathVariable Long locationId, @PathVariable Long userId) {
        boolean removed = locationService.removeUserAccessFromLocation(locationId, userId);
        return removed ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
