package dev.trifonov.catalog_service.controller;

import dev.trifonov.catalog_service.feign.CartFeignClient;
import dev.trifonov.catalog_service.kafka.producer.NewFavoriteProductKafkaSender;
import dev.trifonov.catalog_service.dto.AddToFavoritesDto;
import dev.trifonov.catalog_service.dto.ProductPreviewDto;
import dev.trifonov.catalog_service.service.api.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;
    private final CartFeignClient cartFeignClient;

    private final NewFavoriteProductKafkaSender newFavoriteProductKafkaSender;

    @Override
    public ProductPreviewDto getProduct(long productId) {
        log.info("Принят запрос на получение товара {}", productId);
        return productService.getProduct(productId);
    }

    @Override
    public List<ProductPreviewDto> getProductsByIds(@RequestHeader Map<String, String> headers, List<Long> productIds) {
        log.info("Headers: {}", headers);
        log.info("Принят запрос на получение списка товаров. productIds: {}", productIds);
        return productService.getProducts(productIds);
    }

    @Override
    public List<ProductPreviewDto> getProductPageByFilters(long categoryId, Pageable pageable, Map<String, String> filters) {
        log.info("Принят запрос на получение списка товаров. Параметры пэйджинга: {} Фильтры: {}", pageable, filters);
        long start = System.currentTimeMillis();
        List<ProductPreviewDto> productPreviewDtos = productService.getProductsPage(pageable, filters);
        System.out.println("time: " + (System.currentTimeMillis() - start));
        return productPreviewDtos;
    }

    @Override
    public ResponseEntity<?> addProductToFavorites(long userId, long productId) {
        log.info("Принят запрос на добавление товара {} в избранное пользователя {}.", productId, userId);
        newFavoriteProductKafkaSender.sendMessage(new AddToFavoritesDto(userId, productId));
        return ResponseEntity.ok("Запрос на добавление товара в избранное успешно обработан.");
    }

    @Override
    public ResponseEntity<?> addProductToCart(long userId, long productId) {
        log.info("Принят запрос на добавление товара {} в корзину пользователя {}.", productId, userId);
        cartFeignClient.addProductToCart(userId, productId);
        return ResponseEntity.ok("Запрос на добавление товара в корзину успешно обработан.");
    }
}
