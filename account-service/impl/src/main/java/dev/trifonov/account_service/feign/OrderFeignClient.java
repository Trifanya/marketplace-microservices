package dev.trifonov.account_service.feign;

import dev.trifonov.order_service.dto.OrderDetailsDto;
import dev.trifonov.order_service.dto.OrderPreviewDto;
import dev.trifonov.order_service.dto.OrderProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "order-service"/*, url = "${feign.order-service.uri}"*/)
public interface OrderFeignClient {

    @GetMapping
    List<OrderPreviewDto> getOrdersByIds(List<Long> ids);

    @PostMapping("/new/{userId}")
    OrderDetailsDto placeAnOrder(@PathVariable long userId, @RequestBody List<OrderProductDto> productsInCart);
}
