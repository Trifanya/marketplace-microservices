package dev.trifonov.account_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@FeignClient(value = "purchase-service", url = "${feign-client.uri.purchase-service}")
public interface PurchaseFeignClient {

    @GetMapping
    List<Object> getPurchasesByIds(Set<Long> ids);
}
