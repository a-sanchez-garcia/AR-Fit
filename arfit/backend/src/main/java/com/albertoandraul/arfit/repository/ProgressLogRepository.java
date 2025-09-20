package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.ProgressLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface ProgressLogRepository extends JpaRepository<ProgressLog, Long> {
    List<ProgressLog> findByUserIdOrderByDateDesc(Long userId);
    List<ProgressLog> findByUserIdAndExerciseIdOrderByDateDesc(Long userId, Long exerciseId);

    @Query("SELECT pl FROM ProgressLog pl WHERE pl.userId = :userId AND pl.exerciseId = :exerciseId ORDER BY pl.date DESC")
    List<ProgressLog> findUserProgressByExercise(@Param("userId") Long userId, @Param("exerciseId") Long exerciseId);

    @Query("SELECT pl FROM ProgressLog pl WHERE pl.userId = :userId AND pl.date >= :startDate ORDER BY pl.date DESC")
    List<ProgressLog> findRecentProgress(@Param("userId") Long userId, @Param("startDate") LocalDate startDate);
}