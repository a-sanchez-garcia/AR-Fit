package com.albertoandraul.arfit.dto;

// request para añadir un ejercicio externo
public class WorkoutExternalExerciseRequestDTO {
    private String externalExerciseId;
    private int orderNumber;
    private int plannedSets;

    // getters y setters

    public String getExternalExerciseId() {
        return externalExerciseId;
    }

    public void setExternalExerciseId(String externalExerciseId) {
        this.externalExerciseId = externalExerciseId;
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