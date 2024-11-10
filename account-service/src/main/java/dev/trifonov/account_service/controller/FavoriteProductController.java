package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.dto.ProductPreview;
import dev.trifonov.account_service.entity.UserFavoriteProduct;
import dev.trifonov.account_service.service.repo.service.FavoriteProductServiceWithRepo;
import dev.trifonov.account_service.service.rest.service.ProductServiceWithRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/{userId}/favorites")
public class FavoriteProductController {

    private final FavoriteProductServiceWithRepo favoriteProductServiceWithRepo;
    private final ProductServiceWithRestClient productServiceWithRestClient;

    @GetMapping
    public List<ProductPreview> getFavorites(@PathVariable("userId") Integer userId) {
        log.trace("userId = " + userId);
        List<Integer> productIds =
                favoriteProductServiceWithRepo.getFavoriteProducts(userId).stream()
                        .map(UserFavoriteProduct::getProductId)
                        .toList();
        return productServiceWithRestClient.getProducts(productIds);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> removeFavoriteProduct(@RequestBody Integer favoriteProductId) {
        log.trace("favoriteProductId = " + favoriteProductId);
        favoriteProductServiceWithRepo.removeProductFromUserFavorites(favoriteProductId);
        return ResponseEntity.ok("Товар был успешно удален из списка избранных товаров пользователя.");
    }
}
