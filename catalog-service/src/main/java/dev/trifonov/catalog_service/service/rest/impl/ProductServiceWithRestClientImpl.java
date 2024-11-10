package dev.trifonov.catalog_service.service.rest.impl;

import dev.trifonov.catalog_service.dto.send_dto.NewFavoriteProduct;
import dev.trifonov.catalog_service.service.rest.service.ProductServiceWithRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
public class ProductServiceWithRestClientImpl implements ProductServiceWithRestClient {

    private final RestClient restClient;

    @Override
    public String addToCart(NewFavoriteProduct product) {
        log.info("product: {}", product);
        String response =
                restClient
                        .post()
                        .uri("http://localhost:8765/account-service/account/product/" +
                                product.userId() + "/cart/" + product.productId())
                        .retrieve()
                        .body(String.class);
        log.info("response: {}", response);
        return response;
    }

}
