package dev.trifonov.order_service.mapper;

import dev.trifonov.order_service.dto.OrderDetailsDto;
import dev.trifonov.order_service.dto.OrderPreviewDto;
import dev.trifonov.order_service.entity.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ModelMapper modelMapper;

    public OrderDetailsDto convertToOrderDetailsDto(Order order) {
        return modelMapper.map(order, OrderDetailsDto.class);
    }

    public OrderPreviewDto convertToOrderPreviewDto(Order order) {
        return modelMapper.map(order, OrderPreviewDto.class);
    }
}
