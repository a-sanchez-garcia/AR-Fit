package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.UserDto;
import com.albertoandraul.arfit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 🔹 Obtener todos los usuarios (solo datos públicos)
    @GetMapping
    public ResponseEntity<List<UserDto.UserResponseDto>> getUsers() {
        List<UserDto.UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // 🔹 Obtener un usuario por username
    @GetMapping("/{username}")
    public ResponseEntity<UserDto.UserResponseDto> getUser(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 🔹 Actualizar parcialmente un usuario (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto.UserResponseDto> patchUser(
            @PathVariable Long id,
            @RequestBody UserDto.UpdateUserRequestDto request) {

        return userService.updateUserPartial(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 🔹 Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // 🔹 Obtener info del usuario autenticado
    @GetMapping("/me")
    public ResponseEntity<UserDto.UserResponseDto> getCurrentUser(Authentication authentication) {
        return userService.findByUsername(authentication.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
