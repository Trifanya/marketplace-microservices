package dev.trifonov.feedback_service.controller;

import dev.trifonov.feedback_service.dto.PostReviewDto;
import dev.trifonov.feedback_service.kafka.producer.NewReviewKafkaProducer;
import dev.trifonov.feedback_service.mapper.ReviewMapper;
import dev.trifonov.feedback_service.service.repo.service.ReviewServiceWithRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class FeedbackController {

    private final ReviewMapper reviewMapper;
    private final ReviewServiceWithRepo reviewServiceWithRepo;
    private final NewReviewKafkaProducer newReviewKafkaProducer;

    @GetMapping("{productId}")
    public List<PostReviewDto> getProductReviews(@PathVariable("productId") Long productId) {
        return reviewServiceWithRepo.getReviews(productId).stream()
                .map(reviewMapper::convertToDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<?> postReview(@RequestBody PostReviewDto dto) {
        reviewServiceWithRepo.postReview(reviewMapper.convertToReview(dto));

        newReviewKafkaProducer.sendMessage(
                dto.getProductId(),
                reviewServiceWithRepo.getProductRating(dto.getProductId()));
        return ResponseEntity.ok("Отзыв успешно опубликован.");
    }
}
