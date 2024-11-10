package dev.trifonov.catalog_service.kafka.consumer;

import dev.trifonov.catalog_service.service.repo.service.ProductService;
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
public class ProductRatingKafkaConsumer {

    private final ProductService productService;

    @KafkaListener(
            topics = "${spring.kafka.topic.product-rating}",
            containerFactory = "productRatingKafkaListenerContainerFactory")
    void newReviewListener(@Payload String message,
                           @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                           @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received message [{}] from partition-{} with offset-{}.",
                message, partition, offset);

        String[] messageParts = message.split(" ");
        long productId = Long.parseLong(messageParts[0]);
        float recalculatedRating = Float.parseFloat(messageParts[1]);

        productService.recalculateRating(productId, recalculatedRating);
    }
}
