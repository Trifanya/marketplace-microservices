package dev.trifonov.catalog_service.kafka.producer;

import dev.trifonov.catalog_service.dto.send_dto.NewFavoriteProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewFavoriteProductKafkaSender {

    @Value("${spring.kafka.topic.favorites}")
    private String topicName;

    private final KafkaTemplate<String, NewFavoriteProduct> kafkaTemplate;

    public void sendMessage(NewFavoriteProduct message) {
        log.info("Sending: " + message);
        kafkaTemplate.send(topicName, 1, "newFavoriteProductKey", message);
    }
}
