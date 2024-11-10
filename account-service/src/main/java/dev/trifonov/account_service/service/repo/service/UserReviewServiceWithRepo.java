package dev.trifonov.account_service.service.repo.service;

import dev.trifonov.account_service.entity.UserReview;

import java.util.List;

public interface UserReviewServiceWithRepo {
    List<UserReview> getUserReviews(Integer userId);
    void addNewUserReview(Integer userId, Integer reviewId);
    void removeUserReview(Integer reviewId);
}
