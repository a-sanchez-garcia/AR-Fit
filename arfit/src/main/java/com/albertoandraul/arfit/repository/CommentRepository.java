package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Encontrar comentarios de un post
    List<Comment> findByPostIdOrderByCreatedAtDesc(Long postId);

    // Contar comentarios de un post
    int countByPostId(Long postId);

}