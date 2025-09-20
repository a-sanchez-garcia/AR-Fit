package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.model.Comment;
import com.albertoandraul.arfit.model.Follow;
import com.albertoandraul.arfit.model.Post;
import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.repository.CommentRepository;
import com.albertoandraul.arfit.repository.FollowRepository;
import com.albertoandraul.arfit.repository.PostRepository;
import com.albertoandraul.arfit.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public PostController(PostRepository postRepository, CommentRepository commentRepository,
                          UserRepository userRepository, FollowRepository followRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    // 🔹 CREAR un nuevo post
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post, Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        post.setUserId(currentUser.getId());
        post.setCreatedAt(LocalDateTime.now());
        post.setLikesCount(0);
        post.setCommentsCount(0);

        Post savedPost = postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    // 🔹 OBTENER todos los posts (feed general)
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        return ResponseEntity.ok(posts);
    }

    // 🔹 OBTENER feed personalizado (posts de usuarios seguidos)
    @GetMapping("/feed")
    public ResponseEntity<List<Post>> getPersonalizedFeed(Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // 1. Buscar las relaciones de Follow en la base de datos
        List<Follow> follows = followRepository.findByFollowerId(currentUser.getId());

        // 2. Convertir esas relaciones en una lista de IDs de usuarios seguidos
        List<Long> followingIds = follows.stream()
                .map(Follow::getFollowedId) // me quedo con followedId de cada Follow
                .toList();

        // 3. Incluir también el propio ID del usuario actual
        List<Long> idsToSearch = new ArrayList<>(followingIds); // porque .toList() es inmutable
        idsToSearch.add(currentUser.getId());

        // 4. Buscar los posts de esos usuarios
        List<Post> feedPosts = postRepository.findPostsByUserIds(idsToSearch);

        return ResponseEntity.ok(feedPosts);
    }


    // 🔹 OBTENER un post específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));
        return ResponseEntity.ok(post);
    }

    // 🔹 ELIMINAR un post
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));

        // Verificar que el usuario es el dueño del post
        if (!post.getUserId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo el autor puede eliminar el post");
        }

        postRepository.delete(post);
        return ResponseEntity.noContent().build();
    }

    // 🔹 COMENTAR en un post
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long id, @RequestBody Comment comment, Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Verificar que el post existe
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));

        comment.setPostId(id);
        comment.setUserId(currentUser.getId());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        // Actualizar contador de comentarios del post
        post.setCommentsCount(commentRepository.countByPostId(id));
        postRepository.save(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    // 🔹 OBTENER comentarios de un post
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getPostComments(@PathVariable Long id) {
        // Verificar que el post existe
        if (!postRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado");
        }

        List<Comment> comments = commentRepository.findByPostIdOrderByCreatedAtDesc(id);
        return ResponseEntity.ok(comments);
    }

    // 🔹 OBTENER posts de un usuario específico
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        List<Post> userPosts = postRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return ResponseEntity.ok(userPosts);
    }
}