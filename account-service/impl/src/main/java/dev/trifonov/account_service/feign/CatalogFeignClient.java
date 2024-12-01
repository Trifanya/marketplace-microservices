package dev.trifonov.account_service.feign;

import dev.trifonov.account_service.config.FeignConfig;
import dev.trifonov.catalog_service.dto.ProductPreviewDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "catalog-service", /*url = "${feign.catalog-service.uri}",*/
             configuration = FeignConfig.class)
public interface CatalogFeignClient {

    @PostMapping("/products")
    List<ProductPreviewDto> getProductsByIds(@RequestBody List<Long> ids);
}
