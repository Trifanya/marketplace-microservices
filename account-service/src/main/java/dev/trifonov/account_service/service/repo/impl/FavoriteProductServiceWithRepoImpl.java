package dev.trifonov.account_service.service.repo.impl;

import dev.trifonov.account_service.entity.UserFavoriteProduct;
import dev.trifonov.account_service.repository.FavoriteProductRepository;
import dev.trifonov.account_service.service.repo.service.FavoriteProductServiceWithRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteProductServiceWithRepoImpl implements FavoriteProductServiceWithRepo {

    private final FavoriteProductRepository favoriteProductRepository;

    @Override
    public List<UserFavoriteProduct> getFavoriteProducts(Integer userId) {
        log.trace("");
        return favoriteProductRepository.findAllByUserId(userId);
    }

    @Override
    public void addNewFavoriteProduct(Integer userId, Integer productId) {
        log.trace("");
        UserFavoriteProduct newUserFavoriteProduct = new UserFavoriteProduct()
                .setUserId(userId)
                .setProductId(productId);
        favoriteProductRepository.save(newUserFavoriteProduct);
    }

    @Override
    public void removeProductFromUserFavorites(Integer favoriteProductId) {
        log.trace("");
        favoriteProductRepository.deleteById(favoriteProductId);
    }
}
