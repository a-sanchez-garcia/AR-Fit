package com.albertoandraul.arfit.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutExternalExercise> exercises = new ArrayList<>();

    private LocalDateTime createdAt;

    public Workout() {}

    public Workout(Long id, String title, String description, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    // Getters y setters
    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<WorkoutExternalExercise> getExercises() { return exercises; }
    public void setExercises(List<WorkoutExternalExercise> exercises) { this.exercises = exercises; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Helper para agregar un WorkoutExternalExercise
    public void addExercise(WorkoutExternalExercise exercise) {
        exercises.add(exercise);
        exercise.setWorkout(this);
    }

    // Helper para eliminar
    public void removeExercise(WorkoutExternalExercise exercise) {
        exercises.remove(exercise);
        exercise.setWorkout(null);
    }
}
