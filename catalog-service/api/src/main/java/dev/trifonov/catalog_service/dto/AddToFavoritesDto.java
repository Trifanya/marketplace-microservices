package dev.trifonov.catalog_service.dto;

public record AddToFavoritesDto(
        Long userId,
        Long productId
) {
}
