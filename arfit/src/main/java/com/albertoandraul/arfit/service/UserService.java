package com.albertoandraul.arfit.service;

import com.albertoandraul.arfit.dto.UserDto;
import java.util.Optional;

public interface UserService {
    UserDto.UserResponseDto register(UserDto.UserRequestDto request);
    Optional<UserDto.UserResponseDto> findByUsername(String username);
    // puedes añadir otros métodos: getAllUsers, deleteUser, etc.
}
