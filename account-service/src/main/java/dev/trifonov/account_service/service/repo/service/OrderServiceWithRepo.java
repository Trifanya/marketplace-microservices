package dev.trifonov.account_service.service.repo.service;

import dev.trifonov.account_service.entity.UserOrder;

import java.util.List;

public interface OrderServiceWithRepo {
    List<UserOrder> getOrders(Integer userId);
    void addNewOrder(Integer userId, Integer orderId);
}
