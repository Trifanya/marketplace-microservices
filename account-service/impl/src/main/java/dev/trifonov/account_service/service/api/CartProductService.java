package dev.trifonov.account_service.service.api;

import dev.trifonov.account_service.entity.UserCartProduct;

import java.util.List;

public interface CartProductService {
    List<UserCartProduct> getUserCartProducts(long userId);
    void addNewCartProduct(long userId, long productId);
    void updateCartProductQuantity(long cartProductId, int newQuantity);
    void removeProductFromUserCart(long cartProductId);
}
