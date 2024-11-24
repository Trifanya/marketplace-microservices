package dev.trifonov.account_service.mapper;

import dev.trifonov.account_service.dto.ProductInCartDto;
import dev.trifonov.account_service.entity.CartProduct;
import dev.trifonov.order_service.dto.OrderProductDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartProductMapper {

    private final ModelMapper modelMapper;

    public OrderProductDto convertToOrderProductDto(ProductInCartDto productInCartDto) {
        return new OrderProductDto()
                .setProductId(productInCartDto.getProductPreviewDto().getProductId())
                .setName(productInCartDto.getProductPreviewDto().getName())
                .setBrand(productInCartDto.getProductPreviewDto().getBrand())
                .setPrice(productInCartDto.getProductPreviewDto().getPrice())
                .setRating(productInCartDto.getProductPreviewDto().getRating())
                .setProductCoverUrl(productInCartDto.getProductPreviewDto().getProductCoverUrl())
                .setAmountInOrder(productInCartDto.getAmountInCart());
    }
}
