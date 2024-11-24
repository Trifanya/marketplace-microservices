package dev.trifonov.feedback_service.controller;

import dev.trifonov.feedback_service.dto.ReviewDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/reviews")
public interface FeedbackController {

    @GetMapping("{productId}")
    List<ReviewDetailsDto> getProductReviews(@PathVariable long productId);

    @PostMapping
    ResponseEntity<?> postReview(@RequestBody ReviewDetailsDto dto);
}
