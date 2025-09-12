package com.albertoandraul.arfit.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long followerId;
    private Long followedId;

    @Column (name = "created_at")
    private LocalDateTime createdAt;

    public Follow() {}

    public Follow(Long id, Long followerId, Long followedId, LocalDateTime createdAt) {
        this.id = id;
        this.followerId = followerId;
        this.followedId = followedId;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFollowerId() { return followerId; }
    public void setFollowerId(Long followerId) { this.followerId = followerId; }

    public Long getFollowedId() { return followedId; }
    public void setFollowedId(Long followedId) { this.followedId = followedId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
