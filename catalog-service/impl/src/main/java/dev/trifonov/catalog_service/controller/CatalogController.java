package dev.trifonov.catalog_service.controller;

import dev.trifonov.catalog_service.feign.CartFeignClient;
import dev.trifonov.catalog_service.kafka.producer.NewFavoriteProductKafkaSender;
import dev.trifonov.catalog_service.send_only_dto.AddToFavoritesDto;
import dev.trifonov.catalog_service.send_only_dto.CategoryPreviewDto;
import dev.trifonov.catalog_service.send_only_dto.ProductPreviewDto;
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
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CartFeignClient cartFeignClient;

    private final NewFavoriteProductKafkaSender newFavoriteProductKafkaSender;

    @GetMapping("/products/{productId}")
    public ProductPreviewDto getProduct(@PathVariable("productId") long productId) throws InterruptedException {
        log.info("Принят запрос на получение товара {}", productId);
        return productService.getProduct(productId);
    }

    @PostMapping("/products")
    public List<ProductPreviewDto> getProductsByIds(@RequestBody Set<Long> productIds) {
        log.info("Принят запрос на получение списка товаров. productIds: {}", productIds);
        return productService.getProducts(productIds);
    }

    @GetMapping("/categories/{categoryId}")
    public List<ProductPreviewDto> getProductPageByFilters(@PageableDefault(size = 15, sort = "id") Pageable pageable,
                                                           @RequestParam Map<String, String> filters) {
        log.info("Принят запрос на получение списка товаров. Параметры пэйджинга: {} Фильтры: {}", pageable, filters);
        return productService.getProductPage(pageable, filters);
    }

    @GetMapping("/categories")
    public List<CategoryPreviewDto> getCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/products/addToFavorites/{userId}/{productId}")
    public ResponseEntity<?> addProductToFavorites(@PathVariable("userId") long userId,
                                                   @PathVariable("productId") long productId) {
        log.info("Принят запрос на добавление товара {} в избранное пользователя {}.", productId, userId);
        newFavoriteProductKafkaSender.sendMessage(new AddToFavoritesDto(userId, productId));
        return ResponseEntity.ok("Запрос на добавление товара в избранное успешно обработан.");
    }

    @PostMapping("/products/addToCart/{userId}/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable("userId") long userId,
                                              @PathVariable("productId") long productId) {
        log.info("Принят запрос на добавление товара {} в корзину пользователя {}.", productId, userId);
        cartFeignClient.addProductToCart(userId, productId);
        return ResponseEntity.ok("Запрос на добавление товара в корзину успешно обработан.");
    }
}
