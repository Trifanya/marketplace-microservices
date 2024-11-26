package dev.trifonov.account_service.dto;

import dev.trifonov.catalog_service.dto.ProductPreviewDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ProductInCartDto {
    private ProductPreviewDto productPreviewDto;
    private Integer amountInCart;
}
