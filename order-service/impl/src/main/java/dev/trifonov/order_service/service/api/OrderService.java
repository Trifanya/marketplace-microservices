package dev.trifonov.order_service.service.api;

import dev.trifonov.account_service.dto.ProductInCartDto;
import dev.trifonov.catalog_service.dto.send_only_dto.ProductPreviewDto;
import dev.trifonov.order_service.dto.OrderDetailsDto;
import dev.trifonov.order_service.dto.OrderPreviewDto;
import dev.trifonov.order_service.dto.OrderProductDto;

import java.util.List;
import java.util.Map;

public interface OrderService {
    OrderDetailsDto getOrderById(long orderId);
    List<OrderPreviewDto> getOrdersByIds(List<Long> ids);
    OrderDetailsDto createOrder(long userId, List<OrderProductDto> productsInCart);
}
