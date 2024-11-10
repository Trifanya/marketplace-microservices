package dev.trifonov.account_service.service.repo.impl;

import dev.trifonov.account_service.entity.UserOrder;
import dev.trifonov.account_service.repository.OrderRepository;
import dev.trifonov.account_service.service.repo.service.OrderServiceWithRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceWithRepoImpl implements OrderServiceWithRepo {

    private final OrderRepository orderRepository;

    @Override
    public List<UserOrder> getOrders(Integer userId) {
        log.trace("");
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public void addNewOrder(Integer userId, Integer orderId) {
        log.trace("");
        UserOrder newUserOrder = new UserOrder()
                .setUserId(userId)
                .setOrderId(orderId);
        orderRepository.save(newUserOrder);
    }
}
