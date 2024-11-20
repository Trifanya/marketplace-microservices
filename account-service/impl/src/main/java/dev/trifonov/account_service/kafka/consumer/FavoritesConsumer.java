package dev.trifonov.account_service.kafka.consumer;

import dev.trifonov.account_service.kafka.message.NewFavoriteProductMessage;
import dev.trifonov.account_service.service.api.FavoriteProductService;
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
public class FavoritesConsumer {

    private final FavoriteProductService favoriteProductService;

    @KafkaListener(
            topics = "${spring.application.kafka.topic.favorites}",
            containerFactory = "favoritesKafkaListenerContainerFactory")
    void newFavoriteProductListener(@Payload NewFavoriteProductMessage message,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                    @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received message [{}] from favorites-consumer-group, partition-{} with offset-{}.",
                message, partition, offset);

        favoriteProductService.addNewFavoriteProduct(message.userId(), message.productId());

        //log.info("Новый товар успешно добавлен в избранное.");
    }
}
