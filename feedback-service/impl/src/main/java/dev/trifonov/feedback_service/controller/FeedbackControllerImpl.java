package dev.trifonov.feedback_service.controller;

import dev.trifonov.feedback_service.dto.ReviewDetailsDto;
import dev.trifonov.feedback_service.kafka.producer.NewReviewKafkaProducer;
import dev.trifonov.feedback_service.mapper.ReviewMapper;
import dev.trifonov.feedback_service.service.api.ReviewServiceWithRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FeedbackControllerImpl implements FeedbackController {

    private final ReviewMapper reviewMapper;
    private final ReviewServiceWithRepo reviewServiceWithRepo;
    private final NewReviewKafkaProducer newReviewKafkaProducer;

    @Override
    public List<ReviewDetailsDto> getProductReviews(long productId) {
        return reviewServiceWithRepo.getReviews(productId).stream()
                .map(reviewMapper::convertToDto)
                .toList();
    }

    @Override
    public ResponseEntity<?> postReview(ReviewDetailsDto dto) {
        reviewServiceWithRepo.postReview(reviewMapper.convertToReview(dto));
        newReviewKafkaProducer.sendMessage(
                dto.getProductId(),
                reviewServiceWithRepo.getProductRating(dto.getProductId()));
        return ResponseEntity.ok("Отзыв успешно опубликован.");
    }
}
