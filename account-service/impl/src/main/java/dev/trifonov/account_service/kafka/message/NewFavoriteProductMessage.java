package dev.trifonov.account_service.kafka.message;

public record NewFavoriteProductMessage(
        Integer userId,
        Integer productId
) {
}
