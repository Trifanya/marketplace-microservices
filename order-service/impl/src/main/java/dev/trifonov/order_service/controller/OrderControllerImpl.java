package dev.trifonov.order_service.controller;

import dev.trifonov.account_service.dto.ProductInCartDto;
import dev.trifonov.catalog_service.dto.send_only_dto.ProductPreviewDto;
import dev.trifonov.order_service.dto.OrderDetailsDto;
import dev.trifonov.order_service.dto.OrderPreviewDto;
import dev.trifonov.order_service.dto.OrderProductDto;
import dev.trifonov.order_service.service.api.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    public OrderDetailsDto getOrder(long orderId) {
        log.info("Принят запрос на получение заказа. orderId: {}", orderId);
        return orderService.getOrderById(orderId);
    }

    @Override
    public List<OrderPreviewDto> getOrdersByIds(List<Long> ids) {
        log.info("Принят запрос на получение списка заказов. ids: {}", ids);
        return orderService.getOrdersByIds(ids);
    }

    @Override
    public OrderDetailsDto placeAnOrder(@PathVariable long userId, List<OrderProductDto> productsInCart) {
        log.info("Принят запрос на создание заказа. dto: {}", productsInCart);
        return orderService.createOrder(userId, productsInCart);
    }
}
