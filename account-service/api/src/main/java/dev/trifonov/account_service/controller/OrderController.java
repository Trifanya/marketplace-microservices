package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.dto.UserOrderDto;
import dev.trifonov.order_service.dto.OrderPreviewDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/account/{userId}/orders")
public interface OrderController {

    @GetMapping
    List<OrderPreviewDto> getUserOrders(@PathVariable long userId);
}
