package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.ProgressLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressLogRepository extends JpaRepository<ProgressLog, Long> {
    List<ProgressLog> findByUser_IdOrderByDateDesc(Long userId);
    List<ProgressLog> findByUser_IdAndExercise_IdOrderByDateDesc(Long userId, Long exerciseId);
}
