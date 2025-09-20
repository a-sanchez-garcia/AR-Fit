package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.WorkoutRequestDTO;
import com.albertoandraul.arfit.dto.WorkoutExerciseRequestDTO;
import com.albertoandraul.arfit.model.*;
import com.albertoandraul.arfit.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutRepository workoutRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    public WorkoutController(WorkoutRepository workoutRepository,
                             WorkoutExerciseRepository workoutExerciseRepository,
                             ExerciseRepository exerciseRepository,
                             UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
    }

    // 🔹 CREAR rutina
    @PostMapping
    public ResponseEntity<Workout> createWorkout(@RequestBody WorkoutRequestDTO request, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Workout workout = new Workout();
        workout.setUserId(user.getId());
        workout.setTitle(request.title());
        workout.setDescription(request.description());
        workout.setCreatedAt(LocalDateTime.now());

        Workout savedWorkout = workoutRepository.save(workout);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWorkout);
    }

    // 🔹 LISTAR rutinas del usuario
    @GetMapping
    public ResponseEntity<List<Workout>> getUserWorkouts(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Workout> workouts = workoutRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
        return ResponseEntity.ok(workouts);
    }

    // 🔹 OBTENER rutina específica
    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkout(@PathVariable Long id, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rutina no encontrada"));

        // Verificar que el usuario es el dueño de la rutina
        if (!workout.getUserId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes acceso a esta rutina");
        }

        return ResponseEntity.ok(workout);
    }

    // 🔹 EDITAR rutina
    @PutMapping("/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @RequestBody WorkoutRequestDTO request, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rutina no encontrada"));

        if (!workout.getUserId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes editar esta rutina");
        }

        workout.setTitle(request.title());
        workout.setDescription(request.description());

        Workout updatedWorkout = workoutRepository.save(workout);
        return ResponseEntity.ok(updatedWorkout);
    }

    // 🔹 ELIMINAR rutina
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long id, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rutina no encontrada"));

        if (!workout.getUserId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes eliminar esta rutina");
        }

        // Eliminar ejercicios asociados primero
        workoutExerciseRepository.deleteByWorkoutId(id);
        workoutRepository.delete(workout);

        return ResponseEntity.noContent().build();
    }

    // 🔹 AÑADIR ejercicio a rutina
    @PostMapping("/{workoutId}/exercises")
    public ResponseEntity<WorkoutExercise> addExerciseToWorkout(@PathVariable Long workoutId,
                                                                @RequestBody WorkoutExerciseRequestDTO request,
                                                                Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Verificar que la rutina existe y pertenece al usuario
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rutina no encontrada"));

        if (!workout.getUserId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes modificar esta rutina");
        }

        // Verificar que el ejercicio existe
        exerciseRepository.findById(request.exerciseId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ejercicio no encontrado"));

        WorkoutExercise workoutExercise = new WorkoutExercise();
        workoutExercise.setWorkoutId(workoutId);
        workoutExercise.setExerciseId(request.exerciseId());
        workoutExercise.setOrderNumber(request.orderNumber());
        workoutExercise.setPlannedSets(request.plannedSets());

        WorkoutExercise savedExercise = workoutExerciseRepository.save(workoutExercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExercise);
    }

    // 🔹 LISTAR ejercicios de rutina
    @GetMapping("/{workoutId}/exercises")
    public ResponseEntity<List<WorkoutExercise>> getWorkoutExercises(@PathVariable Long workoutId, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Verificar que la rutina existe y pertenece al usuario
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rutina no encontrada"));

        if (!workout.getUserId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes acceso a esta rutina");
        }

        List<WorkoutExercise> exercises = workoutExerciseRepository.findByWorkoutIdOrderByOrderNumber(workoutId);
        return ResponseEntity.ok(exercises);
    }
}