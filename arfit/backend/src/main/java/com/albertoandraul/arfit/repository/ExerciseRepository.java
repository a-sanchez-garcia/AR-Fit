package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByName(String name);
    List<Exercise> findByNameContainingIgnoreCase(String name);
    List<Exercise> findByMuscleGroup(String muscleGroup);
}
