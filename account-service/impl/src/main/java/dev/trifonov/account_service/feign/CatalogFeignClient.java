package dev.trifonov.account_service.feign;

import dev.trifonov.catalog_service.dto.ProductPreviewDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@FeignClient(value = "catalog-service", url = "${feign-client.uri.catalog-service}")
public interface CatalogFeignClient {

    @GetMapping("/products")
    List<ProductPreviewDto> getProductsByIds(Set<Long> ids);
}
