package com.albertoandraul.arfit.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 50)
    private String muscleGroup;

    @Column(length = 100)
    private String equipment;

    // Relaciones
    @OneToMany(mappedBy = "exercise")
    private List<Record> records;

    @OneToMany(mappedBy = "exercise")
    private List<WorkoutExercise> workoutExercises;

    // Getters y Setters
}
