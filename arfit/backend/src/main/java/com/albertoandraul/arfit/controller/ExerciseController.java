package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.model.Exercise;
import com.albertoandraul.arfit.repository.ExerciseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    private final ExerciseRepository repo;
    public ExerciseController(ExerciseRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Exercise> all() { return repo.findAll(); }
}
