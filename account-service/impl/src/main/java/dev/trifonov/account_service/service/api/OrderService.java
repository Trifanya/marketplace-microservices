package dev.trifonov.account_service.service.api;

import dev.trifonov.account_service.entity.UserOrder;

import java.util.List;

public interface OrderService {
    List<UserOrder> getOrders(long userId);
    void addNewOrder(long userId, long orderId);
}
