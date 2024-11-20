package dev.trifonov.catalog_service.service.impl;

import dev.trifonov.catalog_service.entity.Feature;
import dev.trifonov.catalog_service.repository.CategoryCharacteristicRepository;
import dev.trifonov.catalog_service.service.api.CategoryCharacteristicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryCharacteristicServiceImpl implements CategoryCharacteristicService {

    private final CategoryCharacteristicRepository categoryCharacteristicRepository;

    @Override
    public List<Feature> getCategoryCharacteristics(Integer categoryId) {
        return categoryCharacteristicRepository.findAllByCategoryId(categoryId);
    }
}
