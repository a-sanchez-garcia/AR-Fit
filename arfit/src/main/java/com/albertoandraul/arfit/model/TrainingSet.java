package com.albertoandraul.arfit.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "training_set")
public class TrainingSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sessionId;
    private Long workoutExerciseId;
    private int setNumber;
    private BigDecimal weight;
    private int reps;

    public TrainingSet() {}

    public TrainingSet(Long id, Long sessionId, Long workoutExerciseId, int setNumber, BigDecimal weight, int reps) {
        this.id = id;
        this.sessionId = sessionId;
        this.workoutExerciseId = workoutExerciseId;
        this.setNumber = setNumber;
        this.weight = weight;
        this.reps = reps;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSessionId() { return sessionId; }
    public void setSessionId(Long sessionId) { this.sessionId = sessionId; }

    public Long getWorkoutExerciseId() { return workoutExerciseId; }
    public void setWorkoutExerciseId(Long workoutExerciseId) { this.workoutExerciseId = workoutExerciseId; }

    public int getSetNumber() { return setNumber; }
    public void setSetNumber(int setNumber) { this.setNumber = setNumber; }

    public BigDecimal getWeight() { return weight; }
    public void setWeight(BigDecimal weight) { this.weight = weight; }

    public int getReps() { return reps; }
    public void setReps(int reps) { this.reps = reps; }
}
