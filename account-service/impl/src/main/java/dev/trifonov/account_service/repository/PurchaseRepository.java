package dev.trifonov.account_service.repository;

import dev.trifonov.account_service.entity.UserPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<UserPurchase, Long> {
    List<UserPurchase> findAllByUserId(long userId);
}
