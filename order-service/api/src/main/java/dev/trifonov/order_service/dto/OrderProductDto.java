package dev.trifonov.order_service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class OrderProductDto {
    private Long productId;
    private String name;
    private String brand;
    private Integer price;
    private Double rating;
    private Integer amountInOrder;
    private String productCoverUrl;
}
