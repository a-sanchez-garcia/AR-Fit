package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.CommentDTO;
import com.albertoandraul.arfit.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long postId,
                                                    @RequestBody String content,
                                                    Authentication auth) {
        CommentDTO created = commentService.createComment(auth.getName(), postId, content);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, Authentication auth) {
        commentService.deleteComment(auth.getName(), id);
        return ResponseEntity.noContent().build();
    }
}
