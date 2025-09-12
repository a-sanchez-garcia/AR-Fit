package com.albertoandraul.arfit.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "progresslog")
public class ProgressLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long exerciseId;
    private LocalDate date;
    private Double weight;
    private Integer reps;
    private Integer sets;

    public ProgressLog() {}

    public ProgressLog(Long id, Long userId, Long exerciseId, LocalDate date, Double weight, Integer reps, Integer sets) {
        this.id = id;
        this.userId = userId;
        this.exerciseId = exerciseId;
        this.date = date;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getExerciseId() { return exerciseId; }
    public void setExerciseId(Long exerciseId) { this.exerciseId = exerciseId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Integer getReps() { return reps; }
    public void setReps(Integer reps) { this.reps = reps; }

    public Integer getSets() { return sets; }
    public void setSets(Integer sets) { this.sets = sets; }
}
