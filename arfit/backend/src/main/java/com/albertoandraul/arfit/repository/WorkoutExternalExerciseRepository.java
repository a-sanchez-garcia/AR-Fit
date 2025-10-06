package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.WorkoutExternalExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutExternalExerciseRepository extends JpaRepository<WorkoutExternalExercise, Long> {
    List<WorkoutExternalExercise> findByWorkoutIdOrderByOrderNumber(Long workoutId);
}
