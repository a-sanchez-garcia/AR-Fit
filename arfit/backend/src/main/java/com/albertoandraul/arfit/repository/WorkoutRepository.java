package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Workout> findByUserId(Long userId);
    int countByUserId(Long userId);

    @Query("SELECT w FROM Workout w WHERE w.userId = :userId OR w.userId IS NULL ORDER BY w.createdAt DESC")
    List<Workout> findByUserIdOrPublic(@Param("userId") Long userId);
}