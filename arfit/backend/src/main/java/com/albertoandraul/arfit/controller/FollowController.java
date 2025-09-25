package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.FollowDTO;
import com.albertoandraul.arfit.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    //Seguir a un usuario
    @PostMapping("/{id}/follow")
    public ResponseEntity<String> followUser(@PathVariable Long id, Authentication auth) {
        return ResponseEntity.ok(followService.follow(auth.getName(), id));
    }

    //Dejar de seguir a un usuario
    @DeleteMapping("/{id}/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable Long id, Authentication auth) {
        return ResponseEntity.ok(followService.unfollow(auth.getName(), id));
    }

    // Obtener seguidores y seguidos de un usuario
    @GetMapping("/{id}/followers")
    public ResponseEntity<List<FollowDTO>> getFollowers(@PathVariable Long id) {
        return ResponseEntity.ok(followService.getFollowers(id));
    }

    // Obtener seguidores y seguidos de un usuario
    @GetMapping("/{id}/following")
    public ResponseEntity<List<FollowDTO>> getFollowing(@PathVariable Long id) {
        return ResponseEntity.ok(followService.getFollowing(id));
    }

    // Comprobar si el usuario autenticado sigue a otro usuario
    @GetMapping("/{id}/is-following")
    public ResponseEntity<Boolean> isFollowing(@PathVariable Long id, Authentication auth) {
        return ResponseEntity.ok(followService.isFollowing(auth.getName(), id));
    }
}
