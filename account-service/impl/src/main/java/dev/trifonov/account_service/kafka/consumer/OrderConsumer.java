package dev.trifonov.account_service.kafka.consumer;

import dev.trifonov.account_service.kafka.message.NewOrderMessage;
import dev.trifonov.account_service.service.api.OrderService;
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

public class OrderConsumer {

    private final OrderService orderService;

    @KafkaListener(
            topics = "${spring.application.kafka.topic.orders}",
            containerFactory = "ordersKafkaListenerContainerFactory")
    void newOrderListener(@Payload NewOrderMessage message,
                          @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                          @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received message [{}] from orders-consumer-group, partition-{} with offset-{}.",
                message, partition, offset);

        orderService.addNewOrder(message.userId(), message.orderId());

        //log.info("Заказ добавлен в историю заказов пользователя.");
    }
}
