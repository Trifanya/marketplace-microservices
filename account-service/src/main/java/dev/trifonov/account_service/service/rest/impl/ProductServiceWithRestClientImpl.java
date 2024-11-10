package dev.trifonov.account_service.service.rest.impl;

import dev.trifonov.account_service.dto.ProductPreview;
import dev.trifonov.account_service.service.rest.service.ProductServiceWithRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceWithRestClientImpl implements ProductServiceWithRestClient {
    public final RestClient restClient;

    @Override
    public List<ProductPreview> getProducts(List<Integer> productIds) {
        log.info("productIds: {}", productIds);
        List<ProductPreview> productPreviews =
                restClient
                        .post()
                        .uri("http://localhost:8765/catalog-service/catalog/products")
                        .body(productIds)
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {
                        });
        log.info("productDtos: {}", productPreviews);
        return productPreviews;
    }
}
