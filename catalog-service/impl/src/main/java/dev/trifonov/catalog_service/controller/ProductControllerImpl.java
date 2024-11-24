package dev.trifonov.catalog_service.controller;

import dev.trifonov.catalog_service.feign.CartFeignClient;
import dev.trifonov.catalog_service.kafka.producer.NewFavoriteProductKafkaSender;
import dev.trifonov.catalog_service.dto.send_only_dto.AddToFavoritesDto;
import dev.trifonov.catalog_service.dto.send_only_dto.CategoryPreviewDto;
import dev.trifonov.catalog_service.dto.send_only_dto.ProductPreviewDto;
import dev.trifonov.catalog_service.service.api.CategoryService;
import dev.trifonov.catalog_service.service.api.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public List<ProductPreviewDto> getProductsByIds(List<Long> productIds) {
        log.info("Принят запрос на получение списка товаров. productIds: {}", productIds);
        return productService.getProducts(productIds);
    }

    @Override
    public List<ProductPreviewDto> getProductPageByFilters(long categoryId, Pageable pageable, Map<String, String> filters) {
        log.info("Принят запрос на получение списка товаров. Параметры пэйджинга: {} Фильтры: {}", pageable, filters);
        return productService.getProductPage(pageable, filters);
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