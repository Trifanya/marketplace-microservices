package dev.trifonov.catalog_service.kafka.producer;

import dev.trifonov.catalog_service.send_only_dto.AddToFavoritesDto;
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

    private final KafkaTemplate<String, AddToFavoritesDto> kafkaTemplate;

    public void sendMessage(AddToFavoritesDto message) {
        kafkaTemplate.send(topicName, 1, "newFavoriteProductKey", message);
        log.info("В брокер отправлено сообщение: " + message);
    }
}
