package dev.trifonov.catalog_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "account-service", url = "${marketplace.feign-client.account-service.uri}")
public interface CartFeignClient {

    @PostMapping("/{userId}/cart/{productId}")
    ResponseEntity<?> addProductToCart(@PathVariable("userId") long userId,
                                       @PathVariable("productId") long productId);
}
