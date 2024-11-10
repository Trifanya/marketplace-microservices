package dev.trifonov.account_service.service.rest.impl;

import dev.trifonov.account_service.dto.OrderDto;
import dev.trifonov.account_service.service.rest.service.OrderServiceWithRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceWithRestClientImpl implements OrderServiceWithRestClient {

    public final RestClient restClient;

    @Override
    public List<OrderDto> getOrders(List<Integer> orderIds) {
        log.info("");
        return restClient
                .get()
                .uri("localhost:8765/order-service/orders")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
