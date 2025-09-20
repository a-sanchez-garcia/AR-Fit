package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    List<WorkoutExercise> findByWorkoutIdOrderByOrderNumber(Long workoutId);
    int countByWorkoutId(Long workoutId);

    @Query("SELECT we FROM WorkoutExercise we WHERE we.workoutId = :workoutId ORDER BY we.orderNumber")
    List<WorkoutExercise> findExercisesByWorkoutId(@Param("workoutId") Long workoutId);

    void deleteByWorkoutId(Long workoutId);
}