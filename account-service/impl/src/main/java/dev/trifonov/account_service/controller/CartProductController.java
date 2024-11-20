package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.entity.UserCartProduct;
import dev.trifonov.account_service.feign.CatalogFeignClient;
import dev.trifonov.account_service.service.api.CartProductService;
import dev.trifonov.catalog_service.send_only_dto.ProductPreviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/{userId}/cart")
public class CartProductController {

    private final CartProductService cartProductService;
    private final CatalogFeignClient catalogFeignClient;

    @GetMapping
    public Map<ProductPreviewDto, Integer> getCart(@PathVariable("userId") long userId) {
        log.trace("Принят запрос на получение корзины пользователя. userId: {}", userId);
        Map<Long, Integer> productIdsAndAmounts =
                cartProductService.getUserCartProducts(userId).stream()
                        .collect(Collectors.toMap(UserCartProduct::getProductId, UserCartProduct::getProductQuantity));

        Map<ProductPreviewDto, Integer> productsAndAmounts = new HashMap<>();
        catalogFeignClient.getProductsByIds(
                new HashSet<>(productIdsAndAmounts.keySet())
        ).forEach(prod -> productsAndAmounts.put(prod, productIdsAndAmounts.get(prod.getId())));

        return productsAndAmounts;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<?> addToCart(@PathVariable("userId") long userId,
                                       @PathVariable("productId") long productId) {
        log.info("Принят запрос на добавление товара в корзину. userId: {} productId: {}", userId, productId);
        cartProductService.addNewCartProduct(userId, productId);
        return ResponseEntity.ok("Товар успешно добавлен в корзину пользователя.");
    }

    @PatchMapping("/{cartProductId}/{newQuantity}")
    public ResponseEntity<?> updateCart(@PathVariable("cartProductId") long cartProductId,
                                        @PathVariable("newQuantity") Integer newProductQuantity) {
        log.info("Принят запрос на обновление состава корзины. cartProductId: {} newProductQuantity: {}",
                cartProductId, newProductQuantity);
        cartProductService.updateCartProductQuantity(cartProductId, newProductQuantity);
        return ResponseEntity.ok("Состав корзины успешно изменен.");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable("productId") long cartProductId) {
        log.info("Принят запрос на удаление товара из корзины. cartProductId: {}", cartProductId);
        cartProductService.removeProductFromUserCart(cartProductId);
        return ResponseEntity.ok("Товар успешно удален из корзины.");
    }
}
