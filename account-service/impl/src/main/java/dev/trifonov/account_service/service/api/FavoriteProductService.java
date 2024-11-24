package dev.trifonov.account_service.service.api;

import dev.trifonov.account_service.entity.FavoriteProduct;

import java.util.List;

public interface FavoriteProductService {
    List<FavoriteProduct> getFavoriteProducts(long userId);
    void addNewFavoriteProduct(long userId, long productId);
    void removeProductFromUserFavorites(long favoriteProductId);
}
