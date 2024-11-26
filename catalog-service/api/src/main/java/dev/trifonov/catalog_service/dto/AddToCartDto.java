package dev.trifonov.catalog_service.dto;

public record AddToCartDto(
        Long userId,
        Long productId
) {
}
