package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.TrainingSessionRequestDTO;
import com.albertoandraul.arfit.dto.TrainingSetRequestDTO;
import com.albertoandraul.arfit.model.*;
import com.albertoandraul.arfit.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/training-sessions")
public class TrainingSessionController {

    private final TrainingSessionRepository trainingSessionRepository;
    private final TrainingSetRepository trainingSetRepository;
    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public TrainingSessionController(TrainingSessionRepository trainingSessionRepository,
                                     TrainingSetRepository trainingSetRepository,
                                     WorkoutRepository workoutRepository,
                                     UserRepository userRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
        this.trainingSetRepository = trainingSetRepository;
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    // 🔹 CREAR sesión de entrenamiento
    @PostMapping
    public ResponseEntity<TrainingSession> createTrainingSession(@RequestBody TrainingSessionRequestDTO request, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Verificar que el workout existe
        workoutRepository.findById(request.getWorkoutId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rutina no encontrada"));

        TrainingSession session = new TrainingSession();
        session.setUserId(user.getId());
        session.setWorkoutId(request.getWorkoutId());
        session.setStartedAt(new Date());

        TrainingSession savedSession = trainingSessionRepository.save(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSession);
    }

    // 🔹 LISTAR sesiones del usuario
    @GetMapping
    public ResponseEntity<List<TrainingSession>> getUserSessions(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<TrainingSession> sessions = trainingSessionRepository.findByUserIdOrderByStartedAtDesc(user.getId());
        return ResponseEntity.ok(sessions);
    }

    // 🔹 OBTENER detalles de sesión
    @GetMapping("/{id}")
    public ResponseEntity<TrainingSession> getTrainingSession(@PathVariable Long id, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        TrainingSession session = trainingSessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sesión no encontrada"));

        if (!session.getUserId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes acceso a esta sesión");
        }

        return ResponseEntity.ok(session);
    }

    // 🔹 FINALIZAR sesión
    @PostMapping("/{id}/finish")
    public ResponseEntity<TrainingSession> finishTrainingSession(@PathVariable Long id, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        TrainingSession session = trainingSessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sesión no encontrada"));

        if (!session.getUserId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes modificar esta sesión");
        }

        session.setFinishedAt(new Date());
        TrainingSession updatedSession = trainingSessionRepository.save(session);

        return ResponseEntity.ok(updatedSession);
    }

    // 🔹 AÑADIR set de entrenamiento
    @PostMapping("/{sessionId}/sets")
    public ResponseEntity<TrainingSet> addTrainingSet(@PathVariable Long sessionId,
                                                      @RequestBody TrainingSetRequestDTO request,
                                                      Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Verificar que la sesión existe y pertenece al usuario
        TrainingSession session = trainingSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sesión no encontrada"));

        if (!session.getUserId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes modificar esta sesión");
        }

        TrainingSet trainingSet = new TrainingSet();
        trainingSet.setSessionId(sessionId);
        trainingSet.setWorkoutExerciseId(request.getWorkoutExerciseId());
        trainingSet.setSetNumber(request.getSetNumber());
        trainingSet.setWeight(request.getWeight());
        trainingSet.setReps(request.getReps());

        TrainingSet savedSet = trainingSetRepository.save(trainingSet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSet);
    }

    // 🔹 OBTENER sets de una sesión
    @GetMapping("/{sessionId}/sets")
    public ResponseEntity<List<TrainingSet>> getTrainingSets(@PathVariable Long sessionId, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Verificar que la sesión existe y pertenece al usuario
        TrainingSession session = trainingSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sesión no encontrada"));

        if (!session.getUserId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes acceso a esta sesión");
        }

        List<TrainingSet> sets = trainingSetRepository.findBySessionIdOrdered(sessionId);
        return ResponseEntity.ok(sets);
    }
}