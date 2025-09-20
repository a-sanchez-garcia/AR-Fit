package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    // Verificar si ya existe un follow entre dos usuarios
    Optional<Follow> findByFollowerIdAndFollowedId(Long followerId, Long followedId);

    // Contar seguidores de un usuario
    int countByFollowedId(Long followedId);

    // Contar usuarios que sigue un usuario
    int countByFollowerId(Long followerId);

    // Verificar si un usuario sigue a otro (boolean)
    boolean existsByFollowerIdAndFollowedId(Long followerId, Long followedId);

    List<Follow> findByFollowerId(Long followerId);
}