package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.model.Exercise;
import com.albertoandraul.arfit.repository.ExerciseRepository;
import com.albertoandraul.arfit.service.ExerciseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Exercise> all() {
        return service.getAll();
    }
}
