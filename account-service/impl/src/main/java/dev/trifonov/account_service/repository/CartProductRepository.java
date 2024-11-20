package dev.trifonov.account_service.repository;

import dev.trifonov.account_service.entity.UserCartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<UserCartProduct, Long> {
    List<UserCartProduct> findAllByUserId(long userId);
}
