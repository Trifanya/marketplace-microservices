package dev.trifonov.catalog_service.controller;

import dev.trifonov.catalog_service.dto.ProductPreviewDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/catalog")
public interface ProductController {

    @GetMapping("/products/{productId}")
    ProductPreviewDto getProduct(@PathVariable long productId);

    @GetMapping("/products/{categoryId}/page")
    List<ProductPreviewDto> getProductPageByFilters(@PathVariable long categoryId,
                                                    @PageableDefault(size = 15, sort = "id") Pageable pageable,
                                                    @RequestParam Map<String, String> filters);

    @PostMapping("/products")
    List<ProductPreviewDto> getProductsByIds(@RequestBody List<Long> productIds);

    @PostMapping("/products/addToFavorites/{userId}/{productId}")
    ResponseEntity<?> addProductToFavorites(@PathVariable("userId") long userId,
                                            @PathVariable("productId") long productId);

    @PostMapping("/products/addToCart/{userId}/{productId}")
    ResponseEntity<?> addProductToCart(@PathVariable("userId") long userId,
                                       @PathVariable("productId") long productId);
}
