package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.model.Follow;
import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.repository.FollowRepository;
import com.albertoandraul.arfit.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/follow")
public class FollowController {


    private final UserRepository userRepository;
    private final FollowRepository followRepository;


    public FollowController(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    // ========== ENDPOINTS DE FOLLOW ==========

    // 🔹 Seguir a un usuario
    @PostMapping("/{id}/follow")
    public ResponseEntity<String> followUser(@PathVariable Long id, Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        User userToFollow = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario a seguir no encontrado"));

        // Validaciones
        if (currentUser.getId().equals(userToFollow.getId())) {
            return ResponseEntity.badRequest().body("No puedes seguirte a ti mismo");
        }

        if (followRepository.existsByFollowerIdAndFollowedId(currentUser.getId(), userToFollow.getId())) {
            return ResponseEntity.badRequest().body("Ya sigues a este usuario");
        }

        // Crear relación de follow
        Follow follow = new Follow();
        follow.setFollowerId(currentUser.getId());
        follow.setFollowedId(userToFollow.getId());
        follow.setCreatedAt(LocalDateTime.now());

        followRepository.save(follow);

        return ResponseEntity.ok("Ahora sigues a: " + userToFollow.getUsername());
    }

    // 🔹 Dejar de seguir a un usuario
    @DeleteMapping("/{id}/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable Long id, Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        User userToUnfollow = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario a dejar de seguir no encontrado"));

        // Buscar y eliminar la relación de follow
        Optional<Follow> follow = followRepository.findByFollowerIdAndFollowedId(
                currentUser.getId(), userToUnfollow.getId());

        if (follow.isEmpty()) {
            return ResponseEntity.badRequest().body("No sigues a este usuario");
        }

        followRepository.delete(follow.get());
        return ResponseEntity.ok("Has dejado de seguir a: " + userToUnfollow.getUsername());
    }

    // 🔹 Obtener contador de seguidores
    @GetMapping("/{id}/followers/count")
    public ResponseEntity<Integer> getFollowersCount(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        int count = followRepository.countByFollowedId(id);
        return ResponseEntity.ok(count);
    }

    // 🔹 Obtener contador de seguidos
    @GetMapping("/{id}/following/count")
    public ResponseEntity<Integer> getFollowingCount(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        int count = followRepository.countByFollowerId(id);
        return ResponseEntity.ok(count);
    }

    // 🔹 Verificar si el usuario actual sigue a otro usuario
    @GetMapping("/{id}/is-following")
    public ResponseEntity<Boolean> isFollowing(@PathVariable Long id, Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        boolean isFollowing = followRepository.existsByFollowerIdAndFollowedId(currentUser.getId(), id);
        return ResponseEntity.ok(isFollowing);
    }

}
