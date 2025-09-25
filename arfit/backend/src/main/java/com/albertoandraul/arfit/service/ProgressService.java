package com.albertoandraul.arfit.service;

import com.albertoandraul.arfit.dto.ProgressLogDTO;
import com.albertoandraul.arfit.model.Exercise;
import com.albertoandraul.arfit.model.ProgressLog;
import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.repository.ExerciseRepository;
import com.albertoandraul.arfit.repository.ProgressLogRepository;
import com.albertoandraul.arfit.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgressService {

    private final ProgressLogRepository progressLogRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    public ProgressService(ProgressLogRepository progressLogRepository,
                           UserRepository userRepository,
                           ExerciseRepository exerciseRepository) {
        this.progressLogRepository = progressLogRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public ProgressLogDTO addProgress(String username, Long exerciseId, BigDecimal weight, Integer reps, Integer sets) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        var exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado"));

        var progress = new ProgressLog();
        progress.setUser(user);
        progress.setExercise(exercise);
        progress.setWeight(weight);
        progress.setReps(reps);
        progress.setSets(sets);
        progress.setDate(LocalDate.now());

        progress = progressLogRepository.save(progress);
        return mapToDTO(progress);
    }

    public List<ProgressLogDTO> getHistory(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return progressLogRepository.findByUser_IdOrderByDateDesc(user.getId())
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ProgressLogDTO> getByExercise(String username, Long exerciseId) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return progressLogRepository.findByUser_IdAndExercise_IdOrderByDateDesc(user.getId(), exerciseId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ProgressLogDTO mapToDTO(ProgressLog log) {
        return new ProgressLogDTO(
                log.getId(),
                log.getUser().getUsername(),
                log.getExercise().getName(),
                log.getDate(),
                log.getWeight(),
                log.getReps(),
                log.getSets()
        );
    }
}
