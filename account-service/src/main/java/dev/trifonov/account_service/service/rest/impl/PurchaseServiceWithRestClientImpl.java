package dev.trifonov.account_service.service.rest.impl;

import dev.trifonov.account_service.dto.PurchaseDto;
import dev.trifonov.account_service.service.rest.service.PurchaseServiceWithRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PurchaseServiceWithRestClientImpl implements PurchaseServiceWithRestClient {

    private final RestClient restClient;

    @Override
    public List<PurchaseDto> getPurchases(List<Integer> purchaseIds) {
        log.info("");
        return restClient
                .get()
                .uri("localhost:8765/catalog-service/catalog")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
