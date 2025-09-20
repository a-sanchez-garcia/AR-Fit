package com.albertoandraul.arfit.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String content;
    private String image;
    private String video;
    private LocalDateTime createdAt;
    private Integer likesCount;
    private Integer commentsCount;

    public Post() {}

    public Post(Long id, Long userId, String content, String image, String video,
                LocalDateTime createdAt, Integer likesCount, Integer commentsCount) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.image = image;
        this.video = video;
        this.createdAt = createdAt;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getVideo() { return video; }
    public void setVideo(String video) { this.video = video; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Integer getLikesCount() { return likesCount; }
    public void setLikesCount(Integer likesCount) { this.likesCount = likesCount; }

    public Integer getCommentsCount() { return commentsCount; }
    public void setCommentsCount(Integer commentsCount) { this.commentsCount = commentsCount; }
}
