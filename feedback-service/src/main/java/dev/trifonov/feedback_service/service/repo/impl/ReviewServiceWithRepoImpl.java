package dev.trifonov.feedback_service.service.repo.impl;

import dev.trifonov.feedback_service.exception.ReviewNotFoundException;
import dev.trifonov.feedback_service.entity.Review;
import dev.trifonov.feedback_service.repository.ReviewRepository;
import dev.trifonov.feedback_service.service.repo.service.ReviewServiceWithRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceWithRepoImpl implements ReviewServiceWithRepo {

    private final ReviewRepository repository;

    @Override
    public Review getReview(Long reviewId) {
        return repository.findById(reviewId)
                .orElseThrow(ReviewNotFoundException::new);
    }

    @Override
    public float getProductRating(long productId) {
        return repository.calculateAvgRatingByProductId(productId);
    }

    @Override
    public List<Review> getReviews(Long productId) {
        return repository.findAllByProductId(productId);
    }

    @Override
    public List<Review> getReviews(List<Long> ids) {
        return repository.findAllByIdIn(ids);
    }

    @Override
    public Review postReview(Review newReview) {
        newReview.setPostedAt(LocalDateTime.now());
        return repository.save(newReview);
    }

    @Override
    public Review updateReview(Long reviewId, Review updatedReview) {
        updatedReview.setId(reviewId);
        return repository.save(updatedReview);
    }

    @Override
    public void deleteReview(Long reviewId) {
        repository.deleteById(reviewId);
    }
}
