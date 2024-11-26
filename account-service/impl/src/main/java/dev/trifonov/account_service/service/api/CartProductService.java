package dev.trifonov.account_service.service.api;

import dev.trifonov.account_service.dto.ProductInCartDto;

import java.util.List;

public interface CartProductService {
    List<ProductInCartDto> getUserCartProducts(long userId);
    void addNewCartProduct(long userId, long productId);
    void placeAnOrder(long userId);
    void updateCartProductQuantity(long cartProductId, int newQuantity);
    void removeProductFromUserCart(long cartProductId);
}
