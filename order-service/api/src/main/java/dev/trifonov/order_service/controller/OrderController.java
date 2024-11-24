package dev.trifonov.order_service.controller;

import dev.trifonov.order_service.dto.OrderDetailsDto;
import dev.trifonov.order_service.dto.OrderPreviewDto;
import dev.trifonov.order_service.dto.OrderProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/orders")
public interface OrderController {

    @GetMapping("/{orderId}")
    OrderDetailsDto getOrder(@PathVariable long orderId);

    @PostMapping
    List<OrderPreviewDto> getOrdersByIds(@RequestBody List<Long> ids);

    @PostMapping("/new/{userId}")
    OrderDetailsDto placeAnOrder(@PathVariable long userId, @RequestBody List<OrderProductDto> productsInCart);
}
