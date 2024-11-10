package dev.trifonov.account_service.dto;

import java.time.LocalDate;

public record PurchaseDto(
        Integer productId,
        String productName,
        Integer price,
        LocalDate boughtAt
) {
}
