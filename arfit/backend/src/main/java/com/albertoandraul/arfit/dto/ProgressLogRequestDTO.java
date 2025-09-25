package com.albertoandraul.arfit.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProgressLogRequestDTO(Long exerciseId, LocalDate date, BigDecimal weight, Integer reps, Integer sets) {}