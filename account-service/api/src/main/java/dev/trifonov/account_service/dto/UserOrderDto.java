package dev.trifonov.account_service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserOrderDto {

    private Long userId;

    private Long orderId;
}
