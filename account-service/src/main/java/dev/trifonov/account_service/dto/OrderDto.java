package dev.trifonov.account_service.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record OrderDto (
        Integer id,
        Integer totalCost,
        LocalDateTime createdAt,
        Map<ProductPreview, Integer> products
) {
}
