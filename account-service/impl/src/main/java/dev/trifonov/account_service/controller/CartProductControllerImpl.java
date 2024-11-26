package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.dto.ProductInCartDto;
import dev.trifonov.account_service.service.api.CartProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CartProductControllerImpl implements CartProductController {

    private final CartProductService cartProductService;

    @Override
    public List<ProductInCartDto> getCart(long userId) {
        log.trace("Принят запрос на получение корзины пользователя. userId: {}", userId);
        return cartProductService.getUserCartProducts(userId);
    }

    @Override
    public ResponseEntity<?> addToCart(long userId, long productId) {
        log.info("Принят запрос на добавление товара в корзину. userId: {} productId: {}", userId, productId);
        cartProductService.addNewCartProduct(userId, productId);
        return ResponseEntity.ok("Товар успешно добавлен в корзину пользователя.");
    }

    @Override
    public ResponseEntity<?> placeAnOrder(long userId) {
        log.info("Принят запрос на оформление заказа. userId: {}", userId);
        cartProductService.placeAnOrder(userId);
        return ResponseEntity.ok("Заказ успешно оформлен.");
    }

    @Override
    public ResponseEntity<?> updateCart(long cartProductId, int newProductQuantity) {
        log.info("Принят запрос на обновление состава корзины. cartProductId: {} newProductQuantity: {}",
                cartProductId, newProductQuantity);
        cartProductService.updateCartProductQuantity(cartProductId, newProductQuantity);
        return ResponseEntity.ok("Состав корзины успешно изменен.");
    }

    @Override
    public ResponseEntity<?> removeFromCart(long cartProductId) {
        log.info("Принят запрос на удаление товара из корзины. cartProductId: {}", cartProductId);
        cartProductService.removeProductFromUserCart(cartProductId);
        return ResponseEntity.ok("Товар успешно удален из корзины.");
    }
}
