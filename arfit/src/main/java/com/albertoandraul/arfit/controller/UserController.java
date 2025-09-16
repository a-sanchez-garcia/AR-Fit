package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 🔹 Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // 🔹 Obtener un usuario por username
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No se ha encontrado el usuario: " + username));
        return ResponseEntity.ok(user);
    }

    // 🔹 Actualizar un usuario completo (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se ha encontrado el usuario: " + id);
        }
        user.setId(id); // aseguramos que actualiza el usuario correcto
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Actualizar parcialmente un usuario (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUser(@PathVariable Long id, @RequestBody User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    if (user.getUsername() != null) existingUser.setUsername(user.getUsername());
                    if (user.getPassword() != null) existingUser.setPassword(user.getPassword());
                    if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
                    userRepository.save(existingUser);
                    return ResponseEntity.ok("Usuario modificado correctamente: " + existingUser.getUsername());
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se ha encontrado el usuario: " + id));
    }

    // 🔹 Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se ha encontrado el usuario: " + id);
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
