package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.entity.*;

import dev.trifonov.account_service.feign.FeedbackFeignClient;
import dev.trifonov.account_service.service.api.ReviewService;
import dev.trifonov.feedback_service.dto.ReviewPreviewDto;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewControllerImpl implements ReviewController {

    private final ReviewService reviewService;
    private final FeedbackFeignClient feedbackFeignClient;

    @Override
    public List<ReviewPreviewDto> getUserReviews(long userId) {
        log.info("Принят запрос на получение списка отзывов пользователя. userId: {}", userId);
        Set<Long> reviewIds =
                reviewService.getUserReviews(userId).stream()
                        .map(UserReview::getId)
                        .collect(Collectors.toSet());
        return feedbackFeignClient.getReviewsByIds(reviewIds);
    }

    @Override
    public ResponseEntity<?> removeReview(long reviewId) {
        log.info("Принят запрос на удаление отзыва. reviewId: {}", reviewId);
        reviewService.removeUserReview(reviewId);
        return ResponseEntity.ok("Запрос успешно обработан.");
    }
}
