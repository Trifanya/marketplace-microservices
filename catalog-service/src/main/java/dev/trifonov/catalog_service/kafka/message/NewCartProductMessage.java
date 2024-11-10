package dev.trifonov.catalog_service.kafka.message;

public record NewCartProductMessage(
        Integer userId,
        Integer productId
) {
}
