package dev.trifonov.account_service.repository;

import dev.trifonov.account_service.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    List<CartProduct> findAllByUserId(long userId);
}
