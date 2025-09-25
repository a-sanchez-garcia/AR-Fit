package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.ProgressLogDTO;
import com.albertoandraul.arfit.dto.ProgressLogRequestDTO;
import com.albertoandraul.arfit.service.ProgressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    // Crear un nuevo registro de progreso usando JSON
    @PostMapping
    public ResponseEntity<ProgressLogDTO> addProgress(
            @RequestBody ProgressLogRequestDTO request,
            Authentication auth) {

        ProgressLogDTO created = progressService.addProgress(
                auth.getName(),
                request.exerciseId(),
                request.weight(),
                request.reps(),
                request.sets()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Historial completo del usuario
    @GetMapping
    public ResponseEntity<List<ProgressLogDTO>> getHistory(Authentication auth) {
        return ResponseEntity.ok(progressService.getHistory(auth.getName()));
    }

    // Historial filtrado por ejercicio
    @GetMapping("/exercise/{exerciseId}")
    public ResponseEntity<List<ProgressLogDTO>> getByExercise(
            @PathVariable Long exerciseId,
            Authentication auth) {

        return ResponseEntity.ok(progressService.getByExercise(auth.getName(), exerciseId));
    }
}
