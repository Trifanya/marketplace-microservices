package dev.trifonov.order_service.service.api;

import dev.trifonov.order_service.dto.OrderDetailsDto;
import dev.trifonov.order_service.dto.OrderPreviewDto;
import dev.trifonov.order_service.dto.OrderProductDto;

import java.util.List;

public interface OrderService {
    OrderDetailsDto getOrderById(long orderId);
    List<OrderPreviewDto> getOrdersByIds(List<Long> ids);
    OrderDetailsDto createOrder(long userId, List<OrderProductDto> productsInCart);
}
