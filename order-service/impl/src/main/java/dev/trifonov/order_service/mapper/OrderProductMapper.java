package dev.trifonov.order_service.mapper;

import dev.trifonov.catalog_service.dto.ProductPreviewDto;
import dev.trifonov.order_service.dto.OrderProductDto;
import dev.trifonov.order_service.entity.OrderProduct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProductMapper {

    private final ModelMapper modelMapper;

    public OrderProductDto convertToOrderProductDto(ProductPreviewDto productPreviewDto, OrderProduct orderProduct) {
        return modelMapper.map(productPreviewDto, OrderProductDto.class)
                .setPrice(orderProduct.getPrice()) // берем цену товара при покупке, а не текущую цену товара, т.к. она может измениться после оформления заказа
                .setAmountInOrder(orderProduct.getAmountInOrder());
    }

    public OrderProduct convertToOrderProduct(OrderProductDto orderProductDto) {
        return new OrderProduct()
                .setProductId(orderProductDto.getProductId())
                .setPrice(orderProductDto.getPrice())
                .setAmountInOrder(orderProductDto.getAmountInOrder());
    }
}
