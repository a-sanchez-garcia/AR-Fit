package com.albertoandraul.arfit.service.impl;

import com.albertoandraul.arfit.dto.UserDto;
import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.repository.UserRepository;
import com.albertoandraul.arfit.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto.UserResponseDto register(UserDto.UserRequestDto request) {
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password())); // aquí ya no dará null
        userRepository.save(user);

        return new UserDto.UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }


    @Override
    public Optional<UserDto.UserResponseDto> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> new UserDto.UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail()
                ));
    }
}
