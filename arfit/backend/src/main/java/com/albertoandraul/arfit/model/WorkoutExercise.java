package com.albertoandraul.arfit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "workout_exercise")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long workoutId;   // FK → Workout
    private Long exerciseId;  // FK → Exercise
    private int orderNumber;  // Orden dentro del workout
    private int plannedSets;  // Número de series planificadas

    public WorkoutExercise() {
    }

    public WorkoutExercise(Long id, Long workoutId, Long exerciseId, int orderNumber, int plannedSets) {
        this.id = id;
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.orderNumber = orderNumber;
        this.plannedSets = plannedSets;
    }

    public Long getId() {
        return id;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
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
