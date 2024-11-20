package dev.trifonov.catalog_service.repository;

import dev.trifonov.catalog_service.entity.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCharacteristicRepository extends JpaRepository<Characteristic, Integer> {
}