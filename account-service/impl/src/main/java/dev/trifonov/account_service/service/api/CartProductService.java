package dev.trifonov.account_service.service.api;

import dev.trifonov.account_service.dto.ProductInCartDto;
import dev.trifonov.catalog_service.dto.send_only_dto.ProductPreviewDto;

import java.util.List;
import java.util.Map;

public interface CartProductService {
    List<ProductInCartDto> getUserCartProducts(long userId);
    void addNewCartProduct(long userId, long productId);
    void placeAnOrder(long userId);
    void updateCartProductQuantity(long cartProductId, int newQuantity);
    void removeProductFromUserCart(long cartProductId);
}
