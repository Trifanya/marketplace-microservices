package dev.trifonov.catalog_service.repository;

import dev.trifonov.catalog_service.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryCharacteristicRepository extends JpaRepository<Feature, Integer> {
    @Query(value = "SELECT id, name, unit FROM category_characteristic WHERE category_id = ?1",
            nativeQuery = true)
    List<Feature> findAllByCategoryId(Integer categoryId);
}