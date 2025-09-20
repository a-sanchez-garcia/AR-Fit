package com.albertoandraul.arfit.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;     // FK → User
    private Long exerciseId; // FK → Exercise

    private BigDecimal weight;
    private int reps;
    private LocalDate date;

    public Record() {
    }

    public Record(Long id, Long userId, Long exerciseId, BigDecimal weight, int reps, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.exerciseId = exerciseId;
        this.weight = weight;
        this.reps = reps;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
