package dev.trifonov.order_service.service.impl;

import dev.trifonov.catalog_service.dto.send_only_dto.ProductPreviewDto;
import dev.trifonov.order_service.dto.OrderDetailsDto;
import dev.trifonov.order_service.dto.OrderPreviewDto;
import dev.trifonov.order_service.dto.OrderProductDto;
import dev.trifonov.order_service.dto.OrderStatus;
import dev.trifonov.order_service.entity.Order;
import dev.trifonov.order_service.entity.OrderProduct;
import dev.trifonov.order_service.feign.ProductFeignClient;
import dev.trifonov.order_service.mapper.OrderMapper;
import dev.trifonov.order_service.mapper.OrderProductMapper;
import dev.trifonov.order_service.repository.OrderRepository;
import dev.trifonov.order_service.service.api.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductFeignClient productFeignClient;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;

    @Override
    @Transactional
    public OrderDetailsDto getOrderById(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заказ с id=" + orderId + " не найден."));
        List<OrderProduct> orderProducts = order.getProducts();
        log.info("orderProducts: {}", orderProducts);
        List<Long> productsIds = orderProducts.stream()
                .map(OrderProduct::getProductId)
                .toList();
        List<ProductPreviewDto> productPreviewDtos = productFeignClient.getProductsByIds(productsIds);
        List<OrderProductDto> orderProductDtos = new ArrayList<>();
        for (int i = 0; i < order.getProducts().size(); i++) {
            orderProductDtos.add(
                    orderProductMapper.convertToOrderProductDto(productPreviewDtos.get(i), orderProducts.get(i)));
        }
        return orderMapper.convertToOrderDetailsDto(order).setProducts(orderProductDtos);
    }

    @Override
    @Transactional
    public List<OrderPreviewDto> getOrdersByIds(List<Long> ids) {
        return orderRepository.findAllById(ids).stream()
                .map(this::getOrderPreviewDto)
                .toList();
    }

    private OrderPreviewDto getOrderPreviewDto(Order order) {
        List<Long> productsIds = order.getProducts().stream()
                .map(OrderProduct::getProductId)
                .toList();
        List<String> productCovers = productFeignClient.getProductsByIds(productsIds).stream()
                .map(ProductPreviewDto::getProductCoverUrl)
                .toList();
        return orderMapper.convertToOrderPreviewDto(order).setProductsCovers(productCovers);
    }

    @Override
    @Transactional
    public OrderDetailsDto createOrder(long userId, List<OrderProductDto> productsInCart) {
        List<OrderProduct> productsInOrder = productsInCart.stream()
                .map(orderProductMapper::convertToOrderProduct)
                .toList();
        Order orderToSave = new Order().setUserId(userId)
                .setPrice(productsInOrder.stream()
                        .mapToInt(OrderProduct::getPrice)
                        .sum())
                .setOrderDateTime(LocalDateTime.now())
                .setDeliveryDateTime(LocalDateTime.now().plusDays(3))
                .setStatus(OrderStatus.PROCESSING)
                .setProducts(productsInOrder);
        productsInOrder.forEach(p -> p.setOrder(orderToSave));
        Order savedOrder = orderRepository.save(orderToSave);
        return orderMapper.convertToOrderDetailsDto(savedOrder);
    }
}
