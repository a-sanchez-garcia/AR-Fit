package com.albertoandraul.arfit.service;

import com.albertoandraul.arfit.dto.CommentDTO;
import com.albertoandraul.arfit.model.Comment;
import com.albertoandraul.arfit.model.Post;
import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.repository.CommentRepository;
import com.albertoandraul.arfit.repository.PostRepository;
import com.albertoandraul.arfit.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public CommentDTO createComment(String username, Long postId, String content) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());

        Comment saved = commentRepository.save(comment);

        return new CommentDTO(saved.getId(), user.getUsername(), saved.getContent(), saved.getCreatedAt());
    }

    public List<CommentDTO> getCommentsByPost(Long postId) {
        return commentRepository.findByPost_IdOrderByCreatedAtAsc(postId)
                .stream()
                .map(c -> new CommentDTO(c.getId(), c.getUser().getUsername(), c.getContent(), c.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public void deleteComment(String username, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentario no encontrado"));

        if (!comment.getUser().getUsername().equals(username)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Solo el autor puede eliminar el comentario");
        }

        commentRepository.delete(comment);
    }
}
