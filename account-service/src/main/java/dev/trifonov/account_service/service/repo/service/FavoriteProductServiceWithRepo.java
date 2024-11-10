package dev.trifonov.account_service.service.repo.service;

import dev.trifonov.account_service.entity.UserFavoriteProduct;

import java.util.List;

public interface FavoriteProductServiceWithRepo {
    List<UserFavoriteProduct> getFavoriteProducts(Integer userId);
    void addNewFavoriteProduct(Integer userId, Integer productId);
    void removeProductFromUserFavorites(Integer favoriteProductId);
}
