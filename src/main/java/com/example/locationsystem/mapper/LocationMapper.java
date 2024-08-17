package com.example.locationsystem.mapper;

import com.example.locationsystem.dto.LocationDTO;
import com.example.locationsystem.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    LocationDTO toDto(Location location);
    Location toEntity(LocationDTO locationDto);

    List<LocationDTO> toDtoList(List<Location> locations);
    List<Location> toEntityList(List<LocationDTO> locationDtos);
}
