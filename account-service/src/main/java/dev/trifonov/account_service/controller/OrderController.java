package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.dto.OrderDto;
import dev.trifonov.account_service.entity.UserOrder;
import dev.trifonov.account_service.service.repo.service.OrderServiceWithRepo;
import dev.trifonov.account_service.service.rest.service.OrderServiceWithRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/{userId}/orders")
public class OrderController {

    private final OrderServiceWithRepo orderServiceWithRepo;
    private final OrderServiceWithRestClient orderServiceWithRestClient;

    @GetMapping
    public List<OrderDto> getUserOrders(@PathVariable("userId") Integer userId) {
        log.trace("userId = " + userId);
        List<Integer> orderIds =
                orderServiceWithRepo.getOrders(userId).stream()
                        .map(UserOrder::getId)
                        .toList();
        return orderServiceWithRestClient.getOrders(orderIds);
    }
}
