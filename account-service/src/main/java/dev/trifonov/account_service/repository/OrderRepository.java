package dev.trifonov.account_service.repository;

import dev.trifonov.account_service.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<UserOrder, Integer> {
    List<UserOrder> findAllByUserId(Integer userId);
}
