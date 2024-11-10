package dev.trifonov.feedback_service.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewReviewKafkaProducer {

    @Value("${spring.kafka.topic.product-rating}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Long productId, Float recalculatedProductRating) {
        kafkaTemplate.send(topicName, productId + " " + recalculatedProductRating);
    }
}
