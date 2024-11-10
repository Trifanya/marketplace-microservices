package dev.trifonov.account_service.kafka.message;

public record NewReviewMessage(
        Integer userId,
        Integer reviewId
) {
}
