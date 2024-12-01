package dev.trifonov.catalog_service.controller;

import dev.trifonov.catalog_service.dto.ProductPreviewDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/products")
public interface ProductController {

    @GetMapping("/{productId}")
    ProductPreviewDto getProduct(@PathVariable long productId);

    @GetMapping("/{categoryId}/page")
    List<ProductPreviewDto> getProductPageByFilters(@PathVariable long categoryId,
                                                    @PageableDefault(size = 15, sort = "id") Pageable pageable,
                                                    @RequestParam Map<String, String> filters);

    @PostMapping
    List<ProductPreviewDto> getProductsByIds(@RequestHeader Map<String, String> headers,
                                             @RequestBody List<Long> productIds);

    @PostMapping("/addToFavorites/{userId}/{productId}")
    ResponseEntity<?> addProductToFavorites(@PathVariable("userId") long userId,
                                            @PathVariable("productId") long productId);

    @PostMapping("/addToCart/{userId}/{productId}")
    ResponseEntity<?> addProductToCart(@PathVariable("userId") long userId,
                                       @PathVariable("productId") long productId);
}
