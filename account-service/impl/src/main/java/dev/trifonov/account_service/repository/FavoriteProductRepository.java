package dev.trifonov.account_service.repository;

import dev.trifonov.account_service.entity.UserFavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteProductRepository extends JpaRepository<UserFavoriteProduct, Long> {
    List<UserFavoriteProduct> findAllByUserId(long userId);
}
