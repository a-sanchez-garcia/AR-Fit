package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.TrainingSetDTO;
import com.albertoandraul.arfit.dto.TrainingSetRequestDTO;
import com.albertoandraul.arfit.dto.TrainingSessionDTO;
import com.albertoandraul.arfit.service.TrainingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training")
public class TrainingSessionController {

    private final TrainingService service;

    public TrainingSessionController(TrainingService service) {
        this.service = service;
    }

    // 🔹 Crear nueva sesión
    @PostMapping("/sessions")
    public ResponseEntity<TrainingSessionDTO> createSession(@RequestParam Long workoutId,
                                                            Authentication auth) {
        TrainingSessionDTO sessionDTO = service.createSession(auth.getName(), workoutId);
        return ResponseEntity.ok(sessionDTO);
    }

    // 🔹 Listar sesiones del usuario
    @GetMapping("/sessions")
    public ResponseEntity<List<TrainingSessionDTO>> getUserSessions(Authentication auth) {
        List<TrainingSessionDTO> sessions = service.getUserSessions(auth.getName());
        return ResponseEntity.ok(sessions);
    }

    // 🔹 Añadir set a sesión
    @PostMapping("/sessions/{sessionId}/sets")
    public ResponseEntity<TrainingSetDTO> addSet(@PathVariable Long sessionId,
                                                 @RequestBody TrainingSetRequestDTO setRequest,
                                                 Authentication auth) {
        TrainingSetDTO setDTO = service.addSet(auth.getName(), sessionId, setRequest);
        return ResponseEntity.ok(setDTO);
    }

    // 🔹 Listar sets de una sesión
    @GetMapping("/sessions/{sessionId}/sets")
    public ResponseEntity<List<TrainingSetDTO>> getSessionSets(@PathVariable Long sessionId,
                                                               Authentication auth) {
        List<TrainingSetDTO> sets = service.getSessionSets(auth.getName(), sessionId);
        return ResponseEntity.ok(sets);
    }

    // 🔹 Finalizar sesión
    @PutMapping("/sessions/{sessionId}/finish")
    public ResponseEntity<TrainingSessionDTO> finishSession(@PathVariable Long sessionId,
                                                            Authentication auth) {
        TrainingSessionDTO sessionDTO = service.finishSession(auth.getName(), sessionId);
        return ResponseEntity.ok(sessionDTO);
    }
}
