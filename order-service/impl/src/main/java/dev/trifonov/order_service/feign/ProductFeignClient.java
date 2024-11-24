package dev.trifonov.order_service.feign;

import dev.trifonov.catalog_service.dto.send_only_dto.ProductPreviewDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "catalog-service", url = "${feign-client.uri.catalog-service}")
public interface ProductFeignClient {

    @GetMapping("/products")
    List<ProductPreviewDto> getProductsByIds(@RequestBody List<Long> ids);
}
