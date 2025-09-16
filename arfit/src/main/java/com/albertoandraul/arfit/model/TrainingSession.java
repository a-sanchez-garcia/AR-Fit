package com.albertoandraul.arfit.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "training_session")
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;    // FK → User
    private Long workoutId; // FK → Workout

    private Date startedAt;
    private Date finishedAt;

    public TrainingSession() {
    }

    public TrainingSession(Long id, Long userId, Long workoutId, Date startedAt, Date finishedAt) {
        this.id = id;
        this.userId = userId;
        this.workoutId = workoutId;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }
}
