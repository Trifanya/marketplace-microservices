package dev.trifonov.account_service.controller;

import dev.trifonov.feedback_service.dto.ReviewPreviewDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/account/{userId}/reviews")
public interface ReviewController {

    @GetMapping
    List<ReviewPreviewDto> getUserReviews(@PathVariable long userId);

    @DeleteMapping("{reviewId}")
    ResponseEntity<?> removeReview(@PathVariable("reviewId") long reviewId);
}
