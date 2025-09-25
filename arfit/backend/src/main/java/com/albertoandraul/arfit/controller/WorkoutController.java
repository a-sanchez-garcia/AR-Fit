package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.WorkoutDTO;
import com.albertoandraul.arfit.dto.WorkoutExerciseDTO;
import com.albertoandraul.arfit.dto.WorkoutExerciseRequestDTO;
import com.albertoandraul.arfit.dto.WorkoutRequestDTO;
import com.albertoandraul.arfit.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService service;

    public WorkoutController(WorkoutService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WorkoutDTO> createWorkout(@RequestBody WorkoutRequestDTO dto, Authentication auth) {
        WorkoutDTO created = service.createWorkout(auth.getName(), dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutDTO>> getWorkouts(Authentication auth) {
        List<WorkoutDTO> workouts = service.getUserWorkouts(auth.getName());
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDTO> getWorkout(@PathVariable Long id, Authentication auth) {
        WorkoutDTO workout = service.getWorkout(auth.getName(), id);
        return ResponseEntity.ok(workout);
    }

    @PostMapping("/{id}/exercises")
    public ResponseEntity<WorkoutExerciseDTO> addExercise(@PathVariable Long id,
                                                          @RequestBody WorkoutExerciseRequestDTO dto,
                                                          Authentication auth) {
        WorkoutExerciseDTO exercise = service.addExercise(auth.getName(), id, dto);
        return ResponseEntity.ok(exercise);
    }

    @GetMapping("/{id}/exercises")
    public ResponseEntity<List<WorkoutExerciseDTO>> getExercises(@PathVariable Long id) {
        List<WorkoutExerciseDTO> exercises = service.getWorkoutExercises(id);
        return ResponseEntity.ok(exercises);
    }
}
