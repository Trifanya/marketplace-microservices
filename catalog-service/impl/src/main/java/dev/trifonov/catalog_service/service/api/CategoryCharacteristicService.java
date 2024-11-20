package dev.trifonov.catalog_service.service.api;

import dev.trifonov.catalog_service.entity.Feature;

import java.util.List;

public interface CategoryCharacteristicService {
    List<Feature> getCategoryCharacteristics(Integer categoryId);
}
