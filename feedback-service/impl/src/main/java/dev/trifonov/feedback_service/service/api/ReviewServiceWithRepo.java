package dev.trifonov.feedback_service.service.api;

import dev.trifonov.feedback_service.entity.Review;

import java.util.List;

public interface ReviewServiceWithRepo {
    Review getReview(Long reviewId);
    float getProductRating(long productId);
    List<Review> getReviews(Long productId);
    List<Review> getReviews(List<Long> ids);
    Review postReview(Review newReview);
    Review updateReview(Long reviewId, Review updatedReview);
    void deleteReview(Long id);
}
