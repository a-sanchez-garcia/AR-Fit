package com.albertoandraul.arfit.dto;

import java.math.BigDecimal;

public class TrainingSetRequestDTO {
    private Long workoutExerciseId;
    private int setNumber;
    private BigDecimal weight;
    private int reps;

    public TrainingSetRequestDTO() {} // necesario para Jackson

    public Long getWorkoutExerciseId() { return workoutExerciseId; }
    public void setWorkoutExerciseId(Long workoutExerciseId) { this.workoutExerciseId = workoutExerciseId; }
    public int getSetNumber() { return setNumber; }
    public void setSetNumber(int setNumber) { this.setNumber = setNumber; }
    public BigDecimal getWeight() { return weight; }
    public void setWeight(BigDecimal weight) { this.weight = weight; }
    public int getReps() { return reps; }
    public void setReps(int reps) { this.reps = reps; }
}
