package dev.trifonov.account_service.service.rest.service;

import dev.trifonov.account_service.dto.OrderDto;

import java.util.List;

public interface OrderServiceWithRestClient {
    List<OrderDto> getOrders(List<Integer> orderIds);

}
