package com.albertoandraul.arfit.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;   // relación con Chat

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender; // relación con User

    private String content;
    private Date createdAt;
    private boolean seen;

    public Message() {}

    public Message(Long id, Chat chat, User sender, String content, Date createdAt, boolean seen) {
        this.id = id;
        this.chat = chat;
        this.sender = sender;
        this.content = content;
        this.createdAt = createdAt;
        this.seen = seen;
    }

    public Long getId() { return id; }

    public Chat getChat() { return chat; }
    public void setChat(Chat chat) { this.chat = chat; }

    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public boolean isSeen() { return seen; }
    public void setSeen(boolean seen) { this.seen = seen; }
}
