package com.albertoandraul.arfit.service;

import com.albertoandraul.arfit.model.ExternalExercise;
import com.albertoandraul.arfit.repository.ExternalExerciseRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ExternalExerciseService {

    private final RestTemplate restTemplate;
    private final ExternalExerciseRepository repository;

    private static final String API_BASE = "https://exercisedb.p.rapidapi.com/exercises";
    private static final String API_HOST = "exercisedb.p.rapidapi.com";
    private static final String API_KEY = "49b40cf06bmsh5e006dffb7b459ap13a998jsn6ce70abd09b7";

    public ExternalExerciseService(RestTemplate restTemplate, ExternalExerciseRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    private HttpEntity<Void> buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", API_HOST);
        headers.set("x-rapidapi-key", API_KEY);
        return new HttpEntity<>(headers);
    }

    // Obtener lista de targets
    public List<String> getTargetMuscles() {
        String url = API_BASE + "/targetList";
        ResponseEntity<String[]> response = restTemplate.exchange(url, HttpMethod.GET, buildHeaders(), String[].class);
        return Arrays.asList(response.getBody());
    }

    // Obtener ejercicios por target
    public List<ExternalExercise> getExercisesByTarget(String target) {
        String url = API_BASE + "/target/" + target;
        ResponseEntity<ExternalExercise[]> response = restTemplate.exchange(url, HttpMethod.GET, buildHeaders(), ExternalExercise[].class);
        List<ExternalExercise> exercises = Arrays.asList(response.getBody());

        // Guardar en BD si no existe
        exercises.forEach(ex -> repository.findById(ex.getId()).orElseGet(() -> repository.save(ex)));

        return exercises;
    }
}
