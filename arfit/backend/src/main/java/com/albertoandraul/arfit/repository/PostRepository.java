package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserIdInOrderByCreatedAtDesc(List<Long> userIds);
    List<Post> findAllByOrderByCreatedAtDesc();
    int countByUser_Id(Long userId);
}
