package dev.trifonov.account_service.feign;

import dev.trifonov.order_service.dto.OrderPreviewDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@FeignClient(value = "order-service", url = "${feign-client.uri.order-service}")
public interface OrderFeignClient {
    @GetMapping("/orders")
    List<OrderPreviewDto> getOrdersByIds(Set<Long> ids);
}
