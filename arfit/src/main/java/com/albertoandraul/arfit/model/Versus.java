package com.albertoandraul.arfit.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "versus")
public class Versus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private User winner;

    // Getters y Setters
}
