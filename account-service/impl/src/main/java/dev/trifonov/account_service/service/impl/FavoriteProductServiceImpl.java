package dev.trifonov.account_service.service.impl;

import dev.trifonov.account_service.entity.UserFavoriteProduct;
import dev.trifonov.account_service.repository.FavoriteProductRepository;
import dev.trifonov.account_service.service.api.FavoriteProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteProductServiceImpl implements FavoriteProductService {

    private final FavoriteProductRepository favoriteProductRepository;

    @Override
    public List<UserFavoriteProduct> getFavoriteProducts(long userId) {
        log.trace("");
        return favoriteProductRepository.findAllByUserId(userId);
    }

    @Override
    public void addNewFavoriteProduct(long userId, long productId) {
        log.trace("");
        UserFavoriteProduct newUserFavoriteProduct = new UserFavoriteProduct()
                .setUserId(userId)
                .setProductId(productId);
        favoriteProductRepository.save(newUserFavoriteProduct);
    }

    @Override
    public void removeProductFromUserFavorites(long favoriteProductId) {
        log.trace("");
        favoriteProductRepository.deleteById(favoriteProductId);
    }
}
