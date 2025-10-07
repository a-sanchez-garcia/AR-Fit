package com.albertoandraul.arfit.service;

import com.albertoandraul.arfit.dto.*;
import com.albertoandraul.arfit.model.*;
import com.albertoandraul.arfit.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepo;
    private final ExternalExerciseRepository externalExerciseRepo;
    private final WorkoutExternalExerciseRepository workoutExternalExerciseRepo;
    private final UserRepository userRepo;

    public WorkoutService(WorkoutRepository workoutRepo,
                          ExternalExerciseRepository externalExerciseRepo,
                          WorkoutExternalExerciseRepository workoutExternalExerciseRepo,
                          UserRepository userRepo) {
        this.workoutRepo = workoutRepo;
        this.externalExerciseRepo = externalExerciseRepo;
        this.workoutExternalExerciseRepo = workoutExternalExerciseRepo;
        this.userRepo = userRepo;
    }

    // ----------------- Crear Workout -----------------
    public WorkoutDTO createWorkout(WorkoutRequestDTO request, String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Workout workout = new Workout();
        workout.setTitle(request.getTitle());
        workout.setDescription(request.getDescription());
        workout.setUser(user);

        if (request.getExercises() != null) {
            for (WorkoutExternalExerciseDTO exDTO : request.getExercises()) {
                ExternalExercise exercise = externalExerciseRepo.findById(exDTO.getExternalExerciseId())
                        .orElseThrow(() -> new RuntimeException("External exercise not found"));

                WorkoutExternalExercise we = new WorkoutExternalExercise(
                        workout,
                        exercise,
                        exDTO.getOrderNumber(),
                        exDTO.getPlannedSets()
                );
                workout.addExercise(we);
            }
        }

        Workout saved = workoutRepo.save(workout);
        return mapToDTO(saved);
    }

    // ----------------- Listar Workouts -----------------
    public List<WorkoutDTO> getWorkouts(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Workout> workouts = workoutRepo.findByUserIdOrderByCreatedAtDesc(user.getId());
        return workouts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // ----------------- Eliminar Workout -----------------
    public void deleteWorkout(Long workoutId) {
        if (!workoutRepo.existsById(workoutId)) {
            throw new RuntimeException("Workout not found");
        }
        workoutRepo.deleteById(workoutId);
    }

    // ----------------- Manejo de ejercicios externos -----------------
    public WorkoutExternalExerciseDTO addExternalExerciseToWorkoutReturnDTO(Long workoutId, WorkoutExternalExerciseRequestDTO dto) {
        Workout workout = workoutRepo.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        ExternalExercise exercise = externalExerciseRepo.findById(dto.getExternalExerciseId())
                .orElseThrow(() -> new RuntimeException("External exercise not found"));

        WorkoutExternalExercise we = new WorkoutExternalExercise(workout, exercise, dto.getOrderNumber(), dto.getPlannedSets());
        WorkoutExternalExercise saved = workoutExternalExerciseRepo.save(we);

        // Convertir a DTO
        WorkoutExternalExerciseDTO response = new WorkoutExternalExerciseDTO();
        response.setId(saved.getId());
        response.setExternalExerciseId(saved.getExternalExercise().getId());
        response.setOrderNumber(saved.getOrderNumber());
        response.setPlannedSets(saved.getPlannedSets());

        return response;
    }

    public List<WorkoutExternalExerciseDTO> getWorkoutExternalExercises(Long workoutId) {
        return workoutExternalExerciseRepo.findByWorkoutIdOrderByOrderNumber(workoutId)
                .stream()
                .map(we -> {
                    WorkoutExternalExerciseDTO dto = new WorkoutExternalExerciseDTO();
                    dto.setId(we.getId());
                    dto.setExternalExerciseId(we.getExternalExercise().getId());
                    dto.setOrderNumber(we.getOrderNumber());
                    dto.setPlannedSets(we.getPlannedSets());
                    return dto;
                }).collect(Collectors.toList());
    }

    // ----------------- Mapeo a DTO -----------------
    private WorkoutDTO mapToDTO(Workout workout) {
        WorkoutDTO dto = new WorkoutDTO();
        dto.setId(workout.getId());
        dto.setTitle(workout.getTitle());
        dto.setDescription(workout.getDescription());
        dto.setExercises(workout.getExercises().stream().map(we -> {
            WorkoutExternalExerciseDTO exDTO = new WorkoutExternalExerciseDTO();
            exDTO.setId(we.getId());
            exDTO.setExternalExerciseId(we.getExternalExercise().getId());
            exDTO.setOrderNumber(we.getOrderNumber());
            exDTO.setPlannedSets(we.getPlannedSets());
            return exDTO;
        }).collect(Collectors.toList()));
        return dto;
    }
}
