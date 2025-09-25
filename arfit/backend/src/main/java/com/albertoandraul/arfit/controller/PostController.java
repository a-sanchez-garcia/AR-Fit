package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.PostDTO;
import com.albertoandraul.arfit.model.Post;
import com.albertoandraul.arfit.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody Post post, Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(auth.getName(), post));
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/feed")
    public ResponseEntity<List<PostDTO>> getFeed(Authentication auth) {
        return ResponseEntity.ok(postService.getPersonalizedFeed(auth.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, Authentication auth) {
        postService.deletePost(auth.getName(), id);
        return ResponseEntity.noContent().build();
    }
}
