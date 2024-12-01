package dev.trifonov.user_service.repository;

import dev.trifonov.user_service.entity.MarketplaceUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MarketplaceUser, Long> {
    Optional<MarketplaceUser> findByEmail(String email);
}
