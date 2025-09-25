package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    // Método generado automáticamente
    List<WorkoutExercise> findByWorkoutIdOrderByOrderNumber(Long workoutId);
    int countByWorkoutId(Long workoutId);

    // Si quieres mantener el método con @Query:
    @Query("SELECT we FROM WorkoutExercise we WHERE we.workout.id = :workoutId ORDER BY we.orderNumber")
    List<WorkoutExercise> findExercisesByWorkoutId(@Param("workoutId") Long workoutId);

    void deleteByWorkoutId(Long workoutId);
}
