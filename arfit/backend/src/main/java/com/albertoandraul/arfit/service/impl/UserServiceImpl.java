package com.albertoandraul.arfit.service.impl;

import com.albertoandraul.arfit.dto.UserDto;
import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.repository.UserRepository;
import com.albertoandraul.arfit.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        return mapToResponse(user);
    }

    @Override
    public List<UserDto.UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto.UserResponseDto> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::mapToResponse);
    }

    @Override
    public Optional<UserDto.UserResponseDto> updateUserPartial(Long id, UserDto.UpdateUserRequestDto request) {
        return userRepository.findById(id)
                .map(user -> {
                    if (request.getUsername() != null) user.setUsername(request.getUsername());
                    if (request.getEmail() != null) user.setEmail(request.getEmail());
                    if (request.getPassword() != null)
                        user.setPassword(passwordEncoder.encode(request.getPassword()));
                    userRepository.save(user);
                    return mapToResponse(user);
                });
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 🔹 Método auxiliar para mapear entidad → DTO
    private UserDto.UserResponseDto mapToResponse(User user) {
        return new UserDto.UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
