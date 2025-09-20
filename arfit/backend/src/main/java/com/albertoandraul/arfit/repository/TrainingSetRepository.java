package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.TrainingSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TrainingSetRepository extends JpaRepository<TrainingSet, Long> {
    List<TrainingSet> findBySessionId(Long sessionId);

    @Query("SELECT ts FROM TrainingSet ts WHERE ts.sessionId = :sessionId ORDER BY ts.workoutExerciseId, ts.setNumber")
    List<TrainingSet> findBySessionIdOrdered(@Param("sessionId") Long sessionId);

    void deleteBySessionId(Long sessionId);
}