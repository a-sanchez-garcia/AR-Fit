package com.albertoandraul.arfit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;          // Nombre del ejercicio
    private String description;   // Descripción
    private String muscleGroup;   // Grupo muscular
    private String equipment;     // Equipo necesario

    public Exercise() {
    }

    public Exercise(Long id, String name, String description, String muscleGroup, String equipment) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
        this.equipment = equipment;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
