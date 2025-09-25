package com.albertoandraul.arfit.dto;

import java.util.Date;
import java.util.List;

public class TrainingSessionDTO {
    private Long id;
    private Long workoutId;
    private Date startedAt;
    private Date finishedAt;
    private List<TrainingSetDTO> sets;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getWorkoutId() { return workoutId; }
    public void setWorkoutId(Long workoutId) { this.workoutId = workoutId; }
    public Date getStartedAt() { return startedAt; }
    public void setStartedAt(Date startedAt) { this.startedAt = startedAt; }
    public Date getFinishedAt() { return finishedAt; }
    public void setFinishedAt(Date finishedAt) { this.finishedAt = finishedAt; }
    public List<TrainingSetDTO> getSets() { return sets; }
    public void setSets(List<TrainingSetDTO> sets) { this.sets = sets; }
}
