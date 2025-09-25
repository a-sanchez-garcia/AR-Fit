package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_IdOrderByCreatedAtAsc(Long postId);
}
