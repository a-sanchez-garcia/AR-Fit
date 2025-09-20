package com.albertoandraul.arfit.dto;

public class TrainingSessionRequestDTO {
    private Long workoutId;

    public TrainingSessionRequestDTO() {} // necesario para Jackson
    public Long getWorkoutId() { return workoutId; }
    public void setWorkoutId(Long workoutId) { this.workoutId = workoutId; }
}