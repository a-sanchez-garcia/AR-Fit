package com.albertoandraul.arfit.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProgressLogDTO(
        Long id,
        String username,
        String exerciseName,
        LocalDate date,
        BigDecimal weight,
        Integer reps,
        Integer sets
) {}
