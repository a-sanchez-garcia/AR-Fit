package com.albertoandraul.arfit.dto;

import java.time.LocalDateTime;

public record CommentDTO(
        Long id,
        String username,
        String content,
        LocalDateTime createdAt
) {}
