package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository; // Repositorio para acceder a los datos de usuarios

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository; // Inyección de dependencia del repositorio
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());// Devuelve todos los usuarios
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .map(ResponseEntity::ok) // Si se encuentra el usuario, devuelve 200 OK con el usuario
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND) // Si no se encuentra, devuelve 404 Not Found
                        .body("No se ha encontrado el usuario: " + username)); // Mensaje de error en el cuerpo
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user) {
        User savedUser = userRepository.save(user); // Guarda el nuevo usuario en la base de datos
        URI location = ServletUriComponentsBuilder // Construye la URI del nuevo recurso creado
                .fromCurrentRequest() // Obtiene la URI de la solicitud actual
                .path("/{id}") // Añade el ID del nuevo usuario al final de la URI
                .buildAndExpand(savedUser.getId())  // Reemplaza {id} con el ID real del usuario guardado
                .toUri(); // Convierte a URI
        return ResponseEntity.created(location).body(savedUser); // Devuelve 201 Created con la ubicación del nuevo recurso y el usuario en el cuerpo
    }

    @PutMapping
    public ResponseEntity<?> putUser(@RequestBody User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user); // Actualiza el usuario existente
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id); // Elimina el usuario por ID
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se ha encontrado el usuario: " + id);
    }

    @PatchMapping
    public ResponseEntity<?> patchUser(@RequestBody User user) {
        return userRepository.findById(user.getId())
                .map(existingUser -> { // Si se encuentra el usuario, actualiza solo los campos no nulos
                    if (user.getUsername() != null) existingUser.setUsername(user.getUsername());
                    if (user.getPassword() != null) existingUser.setPassword(user.getPassword());
                    userRepository.save(existingUser);
                    return ResponseEntity.ok("Usuario modificado correctamente: " + existingUser.getUsername());
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se ha encontrado el usuario: " + user.getId()));
    }
}
