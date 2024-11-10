package dev.trifonov.account_service.kafka.message;

public record NewOrderMessage(
        Integer userId,
        Integer orderId
) {
}
