package dev.trifonov.account_service.service.api;

import dev.trifonov.account_service.entity.UserFavoriteProduct;

import java.util.List;

public interface FavoriteProductService {
    List<UserFavoriteProduct> getFavoriteProducts(long userId);
    void addNewFavoriteProduct(long userId, long productId);
    void removeProductFromUserFavorites(long favoriteProductId);
}
