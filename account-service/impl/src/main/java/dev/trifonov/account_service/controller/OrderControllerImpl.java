package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.dto.UserOrderDto;
import dev.trifonov.account_service.entity.UserOrder;
import dev.trifonov.account_service.feign.OrderFeignClient;
import dev.trifonov.account_service.service.api.OrderService;
import dev.trifonov.order_service.dto.OrderPreviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;
    private final OrderFeignClient orderFeignClient;

    @Override
    public List<OrderPreviewDto> getUserOrders(long userId) {
        log.info("Принят запрос на получение списка заказов пользователя. userId: {}", userId);
        List<Long> orderIds =
                orderService.getOrders(userId).stream()
                        .map(UserOrder::getId)
                        .toList();
        return orderFeignClient.getOrdersByIds(orderIds);
    }
}
