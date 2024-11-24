package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.dto.ProductInCartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/account/{userId}/cart")
public interface CartProductController {

    @GetMapping
    List<ProductInCartDto> getCart(@PathVariable long userId);

    @PostMapping("/{productId}")
    ResponseEntity<?> addToCart(@PathVariable("userId") long userId,
                                @PathVariable("productId") long productId);

    @PostMapping
    ResponseEntity<?> placeAnOrder(@PathVariable long userId);

    @PatchMapping("/{cartProductId}/{newQuantity}")
    ResponseEntity<?> updateCart(@PathVariable("cartProductId") long cartProductId,
                                 @PathVariable("newQuantity") int newProductQuantity);

    @DeleteMapping("/{productId}")
    ResponseEntity<?> removeFromCart(@PathVariable("productId") long cartProductId);
}
