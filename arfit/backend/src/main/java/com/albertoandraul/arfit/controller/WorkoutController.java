package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.*;
import com.albertoandraul.arfit.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    // 🔹 Crear nuevo workout
    @PostMapping
    public ResponseEntity<WorkoutDTO> createWorkout(@RequestBody WorkoutRequestDTO request,
                                                    Authentication authentication) {
        WorkoutDTO dto = workoutService.createWorkout(request, authentication.getName());
        return ResponseEntity.ok(dto);
    }

    // 🔹 Listar workouts del usuario autenticado
    @GetMapping
    public ResponseEntity<List<WorkoutDTO>> getWorkouts(Authentication authentication) {
        List<WorkoutDTO> workouts = workoutService.getWorkouts(authentication.getName());
        return ResponseEntity.ok(workouts);
    }

    // 🔹 Eliminar un workout por ID del workout
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Añadir ejercicio externo a un workout existente
    @PostMapping("/{workoutId}/external-exercises")
    public ResponseEntity<WorkoutExternalExerciseDTO> addExternalExercise(@PathVariable Long workoutId,
                                                                          @RequestBody WorkoutExternalExerciseRequestDTO dto) {
        WorkoutExternalExerciseDTO created = workoutService.addExternalExerciseToWorkoutReturnDTO(workoutId, dto);
        return ResponseEntity.ok(created);
    }

    // 🔹 Listar ejercicios externos de un workout
    @GetMapping("/{workoutId}/external-exercises")
    public ResponseEntity<List<WorkoutExternalExerciseDTO>> getExternalExercises(@PathVariable Long workoutId) {
        List<WorkoutExternalExerciseDTO> exercises = workoutService.getWorkoutExternalExercises(workoutId);
        return ResponseEntity.ok(exercises);
    }
}


