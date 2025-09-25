package com.albertoandraul.arfit.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PostDTO(
        Long id,
        String username,
        String content,
        String image,
        String video,
        LocalDateTime createdAt,
        Integer likesCount,
        Integer commentsCount,
        List<CommentDTO> comments
) {}
