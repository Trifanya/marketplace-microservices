package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.entity.UserFavoriteProduct;
import dev.trifonov.account_service.feign.CatalogFeignClient;
import dev.trifonov.account_service.service.api.FavoriteProductService;
import dev.trifonov.account_service.service.rest.service.ProductServiceWithRestClient;
import dev.trifonov.catalog_service.send_only_dto.ProductPreviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/{userId}/favorites")
public class FavoriteProductController {

    private final FavoriteProductService favoriteProductService;
    private final CatalogFeignClient catalogFeignClient;

    @GetMapping
    public List<ProductPreviewDto> getFavorites(@PathVariable("userId") long userId) {
        log.info("Принят запрос на получение избранного пользователя. userId: {}", userId);
        Set<Long> productIds =
                favoriteProductService.getFavoriteProducts(userId).stream()
                        .map(UserFavoriteProduct::getProductId)
                        .collect(Collectors.toSet());
        return catalogFeignClient.getProductsByIds(productIds);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> removeFavoriteProduct(@RequestBody long favoriteProductId) {
        log.info("Принят запрос на удаление товара из избранного. favoriteProductId: {}", favoriteProductId);
        favoriteProductService.removeProductFromUserFavorites(favoriteProductId);
        return ResponseEntity.ok("Товар был успешно удален из списка избранных товаров пользователя.");
    }
}
