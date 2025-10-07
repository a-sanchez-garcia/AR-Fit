package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.model.ExternalExercise;
import com.albertoandraul.arfit.service.ExternalExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/external-exercises")
public class ExternalExerciseController {

    private final ExternalExerciseService service;

    public ExternalExerciseController(ExternalExerciseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ExternalExercise>> getAllExercises() {
        List<String> targets = service.getTargetMuscles();
        List<ExternalExercise> allExercises = targets.stream()
                .flatMap(target -> service.getExercisesByTarget(target).stream())
                .toList();
        return ResponseEntity.ok(allExercises);
    }

    @GetMapping("/target/{target}")
    public ResponseEntity<List<ExternalExercise>> getExercisesByTarget(@PathVariable String target) {
        List<ExternalExercise> exercises = service.getExercisesByTarget(target);
        return ResponseEntity.ok(exercises);
    }
}
