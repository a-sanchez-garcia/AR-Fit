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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExternalExercise exercise;

    private BigDecimal weight;
    private int reps;
    private LocalDate date;

    public Record() {
    }

    public Record(Long id, User user, ExternalExercise exercise, BigDecimal weight, int reps, LocalDate date) {
        this.id = id;
        this.user = user;
        this.exercise = exercise;
        this.weight = weight;
        this.reps = reps;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ExternalExercise getExercise() {
        return exercise;
    }

    public void setExercise(ExternalExercise exercise) {
        this.exercise = exercise;
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
