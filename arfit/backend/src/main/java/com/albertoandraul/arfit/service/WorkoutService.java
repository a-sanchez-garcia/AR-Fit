package com.albertoandraul.arfit.service;

import com.albertoandraul.arfit.dto.WorkoutDTO;
import com.albertoandraul.arfit.dto.WorkoutExerciseDTO;
import com.albertoandraul.arfit.dto.WorkoutExerciseRequestDTO;
import com.albertoandraul.arfit.dto.WorkoutRequestDTO;
import com.albertoandraul.arfit.model.*;
import com.albertoandraul.arfit.repository.ExerciseRepository;
import com.albertoandraul.arfit.repository.UserRepository;
import com.albertoandraul.arfit.repository.WorkoutExerciseRepository;
import com.albertoandraul.arfit.repository.WorkoutRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepo;
    private final WorkoutExerciseRepository workoutExerciseRepo;
    private final ExerciseRepository exerciseRepo;
    private final UserRepository userRepo;

    public WorkoutService(WorkoutRepository workoutRepo,
                          WorkoutExerciseRepository workoutExerciseRepo,
                          ExerciseRepository exerciseRepo,
                          UserRepository userRepo) {
        this.workoutRepo = workoutRepo;
        this.workoutExerciseRepo = workoutExerciseRepo;
        this.exerciseRepo = exerciseRepo;
        this.userRepo = userRepo;
    }

    // Crear un workout
    public WorkoutDTO createWorkout(String username, WorkoutRequestDTO dto) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Workout workout = new Workout();
        workout.setUser(user);
        workout.setTitle(dto.getTitle());
        workout.setDescription(dto.getDescription());

        Workout saved = workoutRepo.save(workout);
        return mapToDTO(saved);
    }

    // Obtener workout por id
    public WorkoutDTO getWorkout(String username, Long id) {
        Workout workout = workoutRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout no encontrado"));
        return mapToDTO(workout);
    }

    // Obtener todos los workouts del usuario
    public List<WorkoutDTO> getUserWorkouts(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Workout> workouts = workoutRepo.findByUserIdOrderByCreatedAtDesc(user.getId());
        return workouts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // Añadir ejercicio a un workout
    public WorkoutExerciseDTO addExercise(String username, Long workoutId, WorkoutExerciseRequestDTO dto) {
        Workout workout = workoutRepo.findById(workoutId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout no encontrado"));

        Exercise exercise = exerciseRepo.findById(dto.getExerciseId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ejercicio no encontrado"));

        WorkoutExercise we = new WorkoutExercise();
        we.setWorkout(workout);
        we.setExercise(exercise);
        we.setOrderNumber(dto.getOrderNumber());
        we.setPlannedSets(dto.getPlannedSets());

        WorkoutExercise saved = workoutExerciseRepo.save(we);
        return mapExerciseToDTO(saved);
    }

    // Obtener ejercicios de un workout
    public List<WorkoutExerciseDTO> getWorkoutExercises(Long workoutId) {
        Workout workout = workoutRepo.findById(workoutId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workout no encontrado"));

        return workout.getExercises().stream()
                .map(this::mapExerciseToDTO)
                .collect(Collectors.toList());
    }

    // Mapear entidad → DTO
    private WorkoutDTO mapToDTO(Workout workout) {
        WorkoutDTO dto = new WorkoutDTO();
        dto.setId(workout.getId());
        dto.setTitle(workout.getTitle());
        dto.setDescription(workout.getDescription());
        if (workout.getExercises() != null) {
            dto.setExercises(workout.getExercises().stream()
                    .map(this::mapExerciseToDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    private WorkoutExerciseDTO mapExerciseToDTO(WorkoutExercise we) {
        WorkoutExerciseDTO dto = new WorkoutExerciseDTO();
        dto.setId(we.getId());
        dto.setExerciseId(we.getExercise().getId());
        dto.setOrderNumber(we.getOrderNumber());
        dto.setPlannedSets(we.getPlannedSets());
        return dto;
    }
}
