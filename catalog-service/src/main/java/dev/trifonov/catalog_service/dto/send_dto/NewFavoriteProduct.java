package dev.trifonov.catalog_service.dto.send_dto;

public record NewFavoriteProduct(
        Integer userId,
        Integer productId
) {
}
