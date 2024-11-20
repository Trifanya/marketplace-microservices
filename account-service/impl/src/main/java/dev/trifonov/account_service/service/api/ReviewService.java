package dev.trifonov.account_service.service.api;

import dev.trifonov.account_service.entity.UserReview;

import java.util.List;

public interface ReviewService {
    List<UserReview> getUserReviews(long userId);
    void addNewUserReview(long userId, long reviewId);
    void removeUserReview(long reviewId);
}
