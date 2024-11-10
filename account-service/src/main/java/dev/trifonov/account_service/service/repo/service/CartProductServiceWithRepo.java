package dev.trifonov.account_service.service.repo.service;

import dev.trifonov.account_service.entity.UserCartProduct;

import java.util.List;

public interface CartProductServiceWithRepo {
    List<UserCartProduct> getUserCartProducts(Integer userId);
    void addNewCartProduct(Integer userId, Integer productId);
    void updateCartProductQuantity(Integer cartProductId, Integer newQuantity);
    void removeProductFromUserCart(Integer cartProductId);
}
