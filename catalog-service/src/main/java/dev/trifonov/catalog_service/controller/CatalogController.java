package dev.trifonov.catalog_service.controller;

import com.querydsl.core.types.Predicate;
import dev.trifonov.catalog_service.dto.send_dto.ProductPreviewDto;
import dev.trifonov.catalog_service.entity.Category;
import dev.trifonov.catalog_service.dto.send_dto.NewFavoriteProduct;
import dev.trifonov.catalog_service.entity.Product;
import dev.trifonov.catalog_service.kafka.producer.NewFavoriteProductKafkaSender;
import dev.trifonov.catalog_service.service.repo.service.CategoryService;
import dev.trifonov.catalog_service.service.repo.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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

    private final NewFavoriteProductKafkaSender newFavoriteProductKafkaSender;

    @GetMapping("/products/{productId}")
    public ProductPreviewDto getProduct(@PathVariable("productId") long productId) throws InterruptedException {
        log.info("productId: {}", productId);
        return productService.getProduct(productId);
    }

    @PostMapping("/products")
    public List<ProductPreviewDto> getProductsByIds(@RequestBody Set<Integer> productIds) {
        log.info("productIds: {}", productIds);
        return productService.getProducts(productIds);
    }

    @GetMapping("/categories/{categoryId}")
    public List<ProductPreviewDto> getProductPageByFilters(@PageableDefault(size = 15, sort = "id") Pageable pageable,
                                                           @QuerydslPredicate(root = Product.class) Predicate predicate
                                                           /*@RequestParam Map<String, String> filters*/) {
        log.info("pageable: {} filters: {}", pageable, predicate);
        //return productService.getProducts(pageable, predicates);
        return Collections.emptyList();
        //return ResponseEntity.ok("Функционал фильтрации на данный момент не доступен.");
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/products/addToFavorites/{userId}/{productId}")
    public ResponseEntity<?> addProductToFavorites(@PathVariable("userId") int userId,
                                                   @PathVariable("productId") int productId) {
        log.info("userId: {}, productId: {}", userId, productId);
        newFavoriteProductKafkaSender.sendMessage(new NewFavoriteProduct(userId, productId));
        return ResponseEntity.ok("Сообщение отправлено в брокер.");
    }

    @PostMapping("/products/addToCart/{userId}/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable("userId") int userId,
                                              @PathVariable("productId") int productId) {
        log.info("userId: {}, productId: {}", userId, productId);

        return ResponseEntity.ok("Сообщение отправлено в брокер.");
    }
}
