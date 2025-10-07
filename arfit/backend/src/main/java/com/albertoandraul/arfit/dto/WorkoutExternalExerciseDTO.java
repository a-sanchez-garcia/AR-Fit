package com.albertoandraul.arfit.dto;

// response para devolver un ejercicio externo
public class WorkoutExternalExerciseDTO {
    private Long id;
    private String externalExerciseId;
    private int orderNumber;
    private int plannedSets;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getExternalExerciseId() { return externalExerciseId; }
    public void setExternalExerciseId(String externalExerciseId) { this.externalExerciseId = externalExerciseId; }
    public int getOrderNumber() { return orderNumber; }
    public void setOrderNumber(int orderNumber) { this.orderNumber = orderNumber; }
    public int getPlannedSets() { return plannedSets; }
    public void setPlannedSets(int plannedSets) { this.plannedSets = plannedSets; }
}
