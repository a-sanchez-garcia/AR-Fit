package com.albertoandraul.arfit.service;

import com.albertoandraul.arfit.dto.UserDto;
import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service // Anotación para marcar esta clase como un servicio de Spring
public class UserService {
    private final UserRepository userRepository; // Inyección de dependencia del repositorio de usuarios

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository; // Constructor para inyectar el UserRepository
    }

    public UserDto.UserResponseDto createUser(UserDto.UserRequestDto request) {
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password()); // aquí luego añadirías encriptación

        User saved = userRepository.save(user);

        return new UserDto.UserResponseDto(saved.getId(), saved.getUsername(), saved.getEmail());
    }
}

