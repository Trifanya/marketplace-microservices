package dev.trifonov.account_service.service.impl;

import dev.trifonov.account_service.entity.UserReview;
import dev.trifonov.account_service.repository.UserReviewRepository;
import dev.trifonov.account_service.service.api.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final UserReviewRepository userReviewRepository;

    @Override
    public List<UserReview> getUserReviews(long userId) {
        log.trace("");
        return userReviewRepository.findAllByUserId(userId);
    }

    @Override
    public void addNewUserReview(long userId, long reviewId) {
        log.trace("");
        UserReview newUserReview = new UserReview()
                .setUserId(userId)
                .setReviewId(reviewId);
        userReviewRepository.save(newUserReview);
    }

    @Override
    public void removeUserReview(long reviewId) {
        log.trace("");
        userReviewRepository.deleteById(reviewId);
    }
}
