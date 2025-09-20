package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.ProgressLogRequestDTO;
import com.albertoandraul.arfit.model.ProgressLog;
import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.repository.ProgressLogRepository;
import com.albertoandraul.arfit.repository.UserRepository;
import com.albertoandraul.arfit.repository.ExerciseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressLogRepository progressLogRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    public ProgressController(ProgressLogRepository progressLogRepository,
                              UserRepository userRepository,
                              ExerciseRepository exerciseRepository) {
        this.progressLogRepository = progressLogRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    // 🔹 AÑADIR registro de progreso (usa fecha actual)
    @PostMapping
    public ResponseEntity<ProgressLog> addProgressLog(@RequestBody ProgressLogRequestDTO request,
                                                      Authentication authentication) {
        // 1️⃣ Obtener el usuario autenticado una sola vez
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // 2️⃣ Verificar que el ejercicio existe
        exerciseRepository.findById(request.exerciseId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ejercicio no encontrado"));

        // 3️⃣ Crear log de progreso
        ProgressLog progressLog = new ProgressLog();
        progressLog.setUserId(user.getId());
        progressLog.setExerciseId(request.exerciseId());
        progressLog.setDate(LocalDate.now()); // ✅ usar fecha actual
        progressLog.setWeight(BigDecimal.valueOf(request.weight()));
        progressLog.setReps(request.reps());
        progressLog.setSets(request.sets());

        ProgressLog savedLog = progressLogRepository.save(progressLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLog);
    }

    // 🔹 LISTAR historial de progreso
    @GetMapping
    public ResponseEntity<List<ProgressLog>> getProgressHistory(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<ProgressLog> progressLogs = progressLogRepository.findByUserIdOrderByDateDesc(user.getId());
        return ResponseEntity.ok(progressLogs);
    }

    // 🔹 OBTENER progreso por ejercicio
    @GetMapping("/exercise/{exerciseId}")
    public ResponseEntity<List<ProgressLog>> getProgressByExercise(@PathVariable Long exerciseId, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        // Verificar que el ejercicio existe
        exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ejercicio no encontrado"));

        List<ProgressLog> progressLogs = progressLogRepository.findByUserIdAndExerciseIdOrderByDateDesc(user.getId(), exerciseId);
        return ResponseEntity.ok(progressLogs);
    }
}