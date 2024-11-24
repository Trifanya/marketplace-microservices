package dev.trifonov.order_service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class OrderPreviewDto {
    private Integer price;
    private LocalDateTime orderDateTime;
    private LocalDateTime deliveryDateTime;
    private OrderStatus status;
    private List<String> productsCovers;
}
