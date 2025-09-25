package com.albertoandraul.arfit.dto;

import java.time.LocalDateTime;

public record FollowDTO(
        Long id,
        String followerUsername,
        String followedUsername,
        LocalDateTime createdAt
) {}
