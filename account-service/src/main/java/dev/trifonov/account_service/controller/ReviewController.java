package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.dto.*;
import dev.trifonov.account_service.entity.*;

import dev.trifonov.account_service.service.repo.service.UserReviewServiceWithRepo;
import dev.trifonov.account_service.service.rest.service.UserReviewServiceWithRestClient;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/{userId}/reviews")
public class ReviewController {

    private final UserReviewServiceWithRepo userReviewServiceWithRepo;
    private final UserReviewServiceWithRestClient userReviewServiceRestClient;

    @GetMapping
    public List<ReviewDto> getUserReviews(@PathVariable("userId") Integer userId) {
        log.trace("userId = " + userId);
        List<Integer> reviewIds =
                userReviewServiceWithRepo.getUserReviews(userId).stream()
                        .map(UserReview::getId)
                        .toList();

        return userReviewServiceRestClient.getReviews(reviewIds);
    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity<?> removeReview(@PathVariable("reviewId") Integer reviewId) {
        log.trace("reviewId = " + reviewId);
        userReviewServiceWithRepo.removeUserReview(reviewId);
        return ResponseEntity.ok("Отзыв был успешно удален.");
    }
}
