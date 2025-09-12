package com.albertoandraul.arfit.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "versus")
public class Versus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "user1_id")
    private Long user1Id;     // FK → User
    @Column (name = "user2_id")
    private Long user2Id;     // FK → User
    private Long exerciseId;  // FK → Exercise
    private Long winnerId;    // FK → User ganador

    private LocalDate date;

    public Versus() {
    }

    public Versus(Long id, Long user1Id, Long user2Id, Long exerciseId, Long winnerId, LocalDate date) {
        this.id = id;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.exerciseId = exerciseId;
        this.winnerId = winnerId;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Long getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Long user1Id) {
        this.user1Id = user1Id;
    }

    public Long getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(Long user2Id) {
        this.user2Id = user2Id;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
