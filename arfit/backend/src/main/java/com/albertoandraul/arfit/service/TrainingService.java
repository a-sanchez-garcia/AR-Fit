package com.albertoandraul.arfit.service;

import com.albertoandraul.arfit.dto.TrainingSessionDTO;
import com.albertoandraul.arfit.dto.TrainingSetDTO;
import com.albertoandraul.arfit.dto.TrainingSetRequestDTO;
import com.albertoandraul.arfit.model.TrainingSession;
import com.albertoandraul.arfit.model.TrainingSet;
import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.model.WorkoutExercise;
import com.albertoandraul.arfit.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class TrainingService {

    private final TrainingSessionRepository sessionRepo;
    private final TrainingSetRepository setRepo;
    private final WorkoutRepository workoutRepo;
    private final UserRepository userRepo;
    private final WorkoutExerciseRepository workoutExerciseRepo;

    public TrainingService(TrainingSessionRepository sessionRepo,
                           TrainingSetRepository setRepo,
                           WorkoutRepository workoutRepo,
                           UserRepository userRepo,
                           WorkoutExerciseRepository workoutExerciseRepo) {
        this.sessionRepo = sessionRepo;
        this.setRepo = setRepo;
        this.workoutRepo = workoutRepo;
        this.userRepo = userRepo;
        this.workoutExerciseRepo = workoutExerciseRepo;
    }

    // 🔹 Crear nueva sesión
    public TrainingSessionDTO createSession(String username, Long workoutId) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        workoutRepo.findById(workoutId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rutina no encontrada"));

        TrainingSession session = new TrainingSession();
        session.setUserId(user.getId());
        session.setWorkoutId(workoutId);
        session.setStartedAt(new Date());
        session = sessionRepo.save(session);

        return toDTO(session, List.of());
    }

    // 🔹 Listar sesiones
    public List<TrainingSessionDTO> getUserSessions(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<TrainingSession> sessions = sessionRepo.findByUserIdOrderByStartedAtDesc(user.getId());

        return sessions.stream()
                .map(s -> toDTO(s, setRepo.findBySessionOrderByWorkoutExerciseIdAscSetNumberAsc(s)))
                .toList();
    }

    // 🔹 Añadir set usando DTO
    public TrainingSetDTO addSet(String username, Long sessionId, TrainingSetRequestDTO dto) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        TrainingSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sesión no encontrada"));

        if (!session.getUserId().equals(user.getId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes acceso a esta sesión");

        WorkoutExercise we = workoutExerciseRepo.findById(dto.getWorkoutExerciseId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ejercicio no encontrado"));

        TrainingSet set = new TrainingSet();
        set.setSession(session);
        set.setWorkoutExercise(we);
        set.setSetNumber(dto.getSetNumber());
        set.setWeight(dto.getWeight());
        set.setReps(dto.getReps());

        return toDTO(setRepo.save(set));
    }

    // 🔹 Listar sets de una sesión
    public List<TrainingSetDTO> getSessionSets(String username, Long sessionId) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        TrainingSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sesión no encontrada"));

        if (!session.getUserId().equals(user.getId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes acceso a esta sesión");

        return setRepo.findBySessionOrderByWorkoutExerciseIdAscSetNumberAsc(session)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // 🔹 Finalizar sesión
    public TrainingSessionDTO finishSession(String username, Long sessionId) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        TrainingSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sesión no encontrada"));

        if (!session.getUserId().equals(user.getId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes acceso a esta sesión");

        session.setFinishedAt(new Date());
        session = sessionRepo.save(session);

        List<TrainingSet> sets = setRepo.findBySessionOrderByWorkoutExerciseIdAscSetNumberAsc(session);
        return toDTO(session, sets);
    }

    // 🔹 Mappers
    public TrainingSetDTO toDTO(TrainingSet set) {
        TrainingSetDTO dto = new TrainingSetDTO();
        dto.setId(set.getId());
        dto.setWorkoutExerciseId(set.getWorkoutExercise().getId());
        dto.setSetNumber(set.getSetNumber());
        dto.setWeight(set.getWeight());
        dto.setReps(set.getReps());
        return dto;
    }

    public TrainingSessionDTO toDTO(TrainingSession session, List<TrainingSet> sets) {
        TrainingSessionDTO dto = new TrainingSessionDTO();
        dto.setId(session.getId());
        dto.setWorkoutId(session.getWorkoutId());
        dto.setStartedAt(session.getStartedAt());
        dto.setFinishedAt(session.getFinishedAt());
        dto.setSets(sets.stream().map(this::toDTO).toList());
        return dto;
    }
}
