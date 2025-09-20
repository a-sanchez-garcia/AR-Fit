package com.albertoandraul.arfit.dto;

import java.time.LocalDate;

public record ProgressLogRequestDTO(Long exerciseId, LocalDate date, Double weight, Integer reps, Integer sets) {}