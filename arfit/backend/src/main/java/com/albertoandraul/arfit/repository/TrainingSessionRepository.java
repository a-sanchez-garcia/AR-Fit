package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
    List<TrainingSession> findByUserIdOrderByStartedAtDesc(Long userId);
    List<TrainingSession> findByWorkoutId(Long workoutId);

    @Query("SELECT ts FROM TrainingSession ts WHERE ts.userId = :userId ORDER BY ts.startedAt DESC")
    List<TrainingSession> findUserSessions(@Param("userId") Long userId);

    int countByUserId(Long userId);
}
