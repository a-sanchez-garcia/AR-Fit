package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // Encontrar posts por usuario
    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);

    // Encontrar posts de usuarios seguidos (para feed)
    @Query("SELECT p FROM Post p WHERE p.userId IN :userIds ORDER BY p.createdAt DESC")
    List<Post> findPostsByUserIds(@Param("userIds") List<Long> userIds);

    // Contar posts de un usuario
    int countByUserId(Long userId);

    List<Post> findAllByOrderByCreatedAtDesc();
}