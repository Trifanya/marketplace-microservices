package dev.trifonov.account_service.service.impl;

import dev.trifonov.account_service.entity.UserOrder;
import dev.trifonov.account_service.repository.OrderRepository;
import dev.trifonov.account_service.service.api.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<UserOrder> getOrders(long userId) {
        log.trace("");
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public void addNewOrder(long userId, long orderId) {
        log.trace("");
        UserOrder newUserOrder = new UserOrder()
                .setUserId(userId)
                .setOrderId(orderId);
        orderRepository.save(newUserOrder);
    }
}
