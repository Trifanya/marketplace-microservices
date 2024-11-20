package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.entity.UserOrder;
import dev.trifonov.account_service.feign.OrderFeignClient;
import dev.trifonov.account_service.service.api.OrderService;
import dev.trifonov.account_service.service.rest.service.OrderServiceWithRestClient;
import dev.trifonov.order_service.dto.OrderPreviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/{userId}/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderFeignClient orderFeignClient;

    @GetMapping
    public List<OrderPreviewDto> getUserOrders(@PathVariable("userId") long userId) {
        log.info("Принят запрос на получение списка заказов пользователя. userId: {}", userId);
        Set<Long> orderIds =
                orderService.getOrders(userId).stream()
                        .map(UserOrder::getId)
                        .collect(Collectors.toSet());
        return orderFeignClient.getOrdersByIds(orderIds);
    }
}
