package com.example.locationsystem.mapper;

import com.example.locationsystem.dto.UserLocationAccessDTO;
import com.example.locationsystem.model.UserLocationAccess;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface  UserLocationAccessMapper {
    UserLocationAccessMapper INSTANCE = Mappers.getMapper(UserLocationAccessMapper.class);

    UserLocationAccessDTO toDto(UserLocationAccess userLocationAccess);
    UserLocationAccess toEntity(UserLocationAccessDTO userLocationAccessDto);

    List<UserLocationAccessDTO> toDtoList(List<UserLocationAccess> userLocationAccessList);
    List<UserLocationAccess> toEntityList(List<UserLocationAccessDTO> userLocationAccessDtoList);
}
