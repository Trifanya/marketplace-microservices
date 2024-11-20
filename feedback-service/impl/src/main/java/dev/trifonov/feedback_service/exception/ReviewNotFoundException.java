package dev.trifonov.feedback_service.exception;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException() {
        super("Отзыв не найден.");
    }
}
