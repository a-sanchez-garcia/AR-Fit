package com.albertoandraul.arfit.dto;

import java.util.List;

public class WorkoutRequestDTO {
    private String title;
    private String description;
    private List<WorkoutExerciseRequestDTO> exercises;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WorkoutExerciseRequestDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<WorkoutExerciseRequestDTO> exercises) {
        this.exercises = exercises;
    }
}
