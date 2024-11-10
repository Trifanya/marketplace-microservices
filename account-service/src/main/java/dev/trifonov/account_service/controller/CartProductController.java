package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.dto.ProductPreview;
import dev.trifonov.account_service.entity.UserCartProduct;
import dev.trifonov.account_service.service.repo.service.CartProductServiceWithRepo;
import dev.trifonov.account_service.service.rest.service.ProductServiceWithRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/{userId}/cart")
public class CartProductController {

    private final CartProductServiceWithRepo cartProductServiceWithRepo;
    private final ProductServiceWithRestClient productServiceWithRestClient;

    @GetMapping
    public Map<ProductPreview, Integer> getCart(@PathVariable("userId") Integer userId) {
        log.trace("userId = " + userId);
        Map<Integer, Integer> productIdsAndAmounts =
                cartProductServiceWithRepo.getUserCartProducts(userId).stream()
                        .collect(Collectors.toMap(UserCartProduct::getId, UserCartProduct::getProductQuantity));

        Map<ProductPreview, Integer> productsAndAmounts = new HashMap<>();
        productServiceWithRestClient.getProducts(
                productIdsAndAmounts.keySet().stream().toList()
        ).forEach(prod -> productsAndAmounts.put(prod, productIdsAndAmounts.get(prod.id())));

        return productsAndAmounts;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<?> addToCart(@PathVariable("userId") Integer userId,
                                       @PathVariable("productId") Integer productId) {
        cartProductServiceWithRepo.addNewCartProduct(userId, productId);
        return ResponseEntity.ok("Товар успешно добавлен в избранное пользователя " + userId);
    }

    @PatchMapping("/{cartProductId}/{newQuantity}")
    public ResponseEntity<?> updateCart(@PathVariable("cartProductId") Integer cartProductId,
                                        @PathVariable("newQuantity") Integer newProductQuantity) {
        log.trace("cartProductId = " + cartProductId + " newProductQuantity = " + newProductQuantity);
        cartProductServiceWithRepo.updateCartProductQuantity(cartProductId, newProductQuantity);
        return ResponseEntity.ok("Состав корзины пользователя был успешно изменен.");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable("productId") Integer cartProductId) {
        log.trace("cartProductId = " + cartProductId);
        cartProductServiceWithRepo.removeProductFromUserCart(cartProductId);
        return ResponseEntity.ok("Товар был успешно удален из корзины пользователя.");
    }
}
