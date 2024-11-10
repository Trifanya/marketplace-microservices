package dev.trifonov.catalog_service.repository;

import dev.trifonov.catalog_service.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
}