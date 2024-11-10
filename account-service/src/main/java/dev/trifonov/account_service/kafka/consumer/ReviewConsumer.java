package dev.trifonov.account_service.kafka.consumer;

import dev.trifonov.account_service.kafka.message.NewReviewMessage;
import dev.trifonov.account_service.service.repo.service.UserReviewServiceWithRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewConsumer {

    private final UserReviewServiceWithRepo userReviewService;

    @KafkaListener(
            topics = "${spring.application.kafka.topic.reviews}",
            containerFactory = "reviewsKafkaListenerContainerFactory")
    void newReviewListener(@Payload NewReviewMessage message,
                           @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                           @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received message [{}] from reviews-consumer-group, partition-{} with offset-{}.",
                message, partition, offset);

        userReviewService.addNewUserReview(message.userId(), message.reviewId());

        //log.info("Отзыв добавлен в историю отзывов пользователя.");
    }
}
