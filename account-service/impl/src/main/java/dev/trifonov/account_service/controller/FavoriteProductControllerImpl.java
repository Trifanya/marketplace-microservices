package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.entity.FavoriteProduct;
import dev.trifonov.account_service.feign.CatalogFeignClient;
import dev.trifonov.account_service.service.api.FavoriteProductService;
import dev.trifonov.catalog_service.dto.send_only_dto.ProductPreviewDto;
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
public class FavoriteProductControllerImpl implements FavoriteProductController {

    private final FavoriteProductService favoriteProductService;
    private final CatalogFeignClient catalogFeignClient;

    @Override
    public List<ProductPreviewDto> getFavorites(long userId) {
        log.info("Принят запрос на получение избранного пользователя. userId: {}", userId);
        Set<Long> productIds =
                favoriteProductService.getFavoriteProducts(userId).stream()
                        .map(FavoriteProduct::getProductId)
                        .collect(Collectors.toSet());
        return catalogFeignClient.getProductsByIds(productIds);
    }

    @Override
    public ResponseEntity<?> removeFavoriteProduct(long favoriteId) {
        log.info("Принят запрос на удаление товара из избранного. favoriteProductId: {}", favoriteId);
        favoriteProductService.removeProductFromUserFavorites(favoriteId);
        return ResponseEntity.ok("Товар был успешно удален из списка избранных товаров пользователя.");
    }
}
