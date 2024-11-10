package dev.trifonov.account_service.dto;

import java.time.LocalDateTime;

public record ReviewDto(
        Integer id,
        Integer stars,
        LocalDateTime createdAt
) {
}
