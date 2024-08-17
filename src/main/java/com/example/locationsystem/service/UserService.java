package com.example.locationsystem.service;

import com.example.locationsystem.dto.UserDTO;
import com.example.locationsystem.mapper.UserMapper;
import com.example.locationsystem.model.User;
import com.example.locationsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO createUser(UserDTO userDto) {
        User user = UserMapper.INSTANCE.toEntity(userDto);
        return UserMapper.INSTANCE.toDto(userRepository.save(user));
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.toDtoList(users);
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper.INSTANCE::toDto);
    }

}
