package dev.trifonov.account_service.controller;

import dev.trifonov.catalog_service.dto.send_only_dto.ProductPreviewDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/account/{userId}/favorites")
public interface FavoriteProductController {

    @GetMapping
    List<ProductPreviewDto> getFavorites(long userId);

    @DeleteMapping("{favoriteId}")
    ResponseEntity<?> removeFavoriteProduct(@PathVariable("favoriteId") long favoriteProductId);
}
