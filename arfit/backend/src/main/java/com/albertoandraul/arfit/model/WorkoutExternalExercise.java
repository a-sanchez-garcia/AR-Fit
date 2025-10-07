package com.albertoandraul.arfit.model;

import jakarta.persistence.*;

@Entity
public class WorkoutExternalExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "external_exercise_id")
    private ExternalExercise externalExercise;

    private int orderNumber;
    private int plannedSets;

    public WorkoutExternalExercise() {}

    public WorkoutExternalExercise(Workout workout, ExternalExercise externalExercise, int orderNumber, int plannedSets) {
        this.workout = workout;
        this.externalExercise = externalExercise;
        this.orderNumber = orderNumber;
        this.plannedSets = plannedSets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public ExternalExercise getExternalExercise() {
        return externalExercise;
    }

    public void setExternalExercise(ExternalExercise externalExercise) {
        this.externalExercise = externalExercise;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getPlannedSets() {
        return plannedSets;
    }

    public void setPlannedSets(int plannedSets) {
        this.plannedSets = plannedSets;
    }
}
