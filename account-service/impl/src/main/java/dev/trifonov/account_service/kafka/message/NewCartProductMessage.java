package dev.trifonov.account_service.kafka.message;

public record NewCartProductMessage(
        Integer userId,
        Integer productId
) {
}
