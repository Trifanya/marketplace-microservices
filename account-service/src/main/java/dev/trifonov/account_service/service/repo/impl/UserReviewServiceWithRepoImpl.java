package dev.trifonov.account_service.service.repo.impl;

import dev.trifonov.account_service.entity.UserReview;
import dev.trifonov.account_service.repository.UserReviewRepository;
import dev.trifonov.account_service.service.repo.service.UserReviewServiceWithRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserReviewServiceWithRepoImpl implements UserReviewServiceWithRepo {

    private final UserReviewRepository userReviewRepository;

    @Override
    public List<UserReview> getUserReviews(Integer userId) {
        log.trace("");
        return userReviewRepository.findAllByUserId(userId);
    }

    @Override
    public void addNewUserReview(Integer userId, Integer reviewId) {
        log.trace("");
        UserReview newUserReview = new UserReview()
                .setUserId(userId)
                .setReviewId(reviewId);
        userReviewRepository.save(newUserReview);
    }

    @Override
    public void removeUserReview(Integer reviewId) {
        log.trace("");
        userReviewRepository.deleteById(reviewId);
    }
}
