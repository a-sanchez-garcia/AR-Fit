package com.albertoandraul.arfit.service;

import com.albertoandraul.arfit.dto.CommentDTO;
import com.albertoandraul.arfit.dto.PostDTO;
import com.albertoandraul.arfit.model.Comment;
import com.albertoandraul.arfit.model.Post;
import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.repository.CommentRepository;
import com.albertoandraul.arfit.repository.FollowRepository;
import com.albertoandraul.arfit.repository.PostRepository;
import com.albertoandraul.arfit.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository,
                       FollowRepository followRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.commentRepository = commentRepository;
    }

    public PostDTO createPost(String username, Post post) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        post.setUser(user);
        post.setLikesCount(0);
        post.setCommentsCount(0);
        Post saved = postRepository.save(post);
        return mapToDTO(saved);
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PostDTO> getPersonalizedFeed(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Long> followingIds = followRepository.findByFollower_Id(user.getId())
                .stream()
                .map(f -> f.getFollowed().getId())
                .collect(Collectors.toList());
        followingIds.add(user.getId()); // incluir propio usuario

        return postRepository.findByUserIdInOrderByCreatedAtDesc(followingIds)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));
        return mapToDTO(post);
    }

    public void deletePost(String username, Long postId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Solo el autor puede eliminar el post");
        }

        postRepository.delete(post);
    }

    private PostDTO mapToDTO(Post post) {
        List<CommentDTO> commentDTOs = commentRepository.findByPost_IdOrderByCreatedAtAsc(post.getId())
                .stream()
                .map(c -> new CommentDTO(c.getId(), c.getUser().getUsername(), c.getContent(), c.getCreatedAt()))
                .collect(Collectors.toList());

        return new PostDTO(
                post.getId(),
                post.getUser().getUsername(),
                post.getContent(),
                post.getImage(),
                post.getVideo(),
                post.getCreatedAt(),
                post.getLikesCount(),
                post.getCommentsCount(),
                commentDTOs
        );
    }
}
