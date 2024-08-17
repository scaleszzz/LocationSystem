package com.example.locationsystem.mapper;

import com.example.locationsystem.dto.UserDTO;
import com.example.locationsystem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);
    User toEntity(UserDTO userDto);

    List<UserDTO> toDtoList(List<User> users);
    List<User> toEntityList(List<UserDTO> userDtoList);
}
