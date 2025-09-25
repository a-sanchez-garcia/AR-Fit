package com.albertoandraul.arfit.service;

import com.albertoandraul.arfit.model.Exercise;
import com.albertoandraul.arfit.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository repo;

    public ExerciseService(ExerciseRepository repo) {
        this.repo = repo;
    }

    public List<Exercise> getAll() {
        return repo.findAll();
    }

    public Exercise getById(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
