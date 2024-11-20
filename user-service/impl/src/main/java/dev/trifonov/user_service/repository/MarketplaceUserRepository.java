package dev.trifonov.user_service.repository;

import dev.trifonov.user_service.entity.MarketplaceUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MarketplaceUserRepository extends CrudRepository<MarketplaceUser, Integer> {
    Optional<MarketplaceUser> findByName(String name);
}
