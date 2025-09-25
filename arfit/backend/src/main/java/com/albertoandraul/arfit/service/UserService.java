package com.albertoandraul.arfit.service;

import com.albertoandraul.arfit.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto.UserResponseDto register(UserDto.UserRequestDto request);

    List<UserDto.UserResponseDto> getAllUsers();

    Optional<UserDto.UserResponseDto> findByUsername(String username);

    Optional<UserDto.UserResponseDto> updateUserPartial(
            Long id,
            UserDto.UpdateUserRequestDto request
    );

    boolean deleteUser(Long id);
}
