package dev.trifonov.catalog_service.service.impl;

import dev.trifonov.catalog_service.entity.Category;
import dev.trifonov.catalog_service.dto.CategoryDetailsDto;
import dev.trifonov.catalog_service.mapper.AbstractMapper;
import dev.trifonov.catalog_service.repository.CategoryRepository;
import dev.trifonov.catalog_service.dto.CategoryPreviewDto;
import dev.trifonov.catalog_service.service.api.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final AbstractMapper<Category, CategoryDetailsDto, CategoryPreviewDto> categoryMapper;

    @Override
    public List<CategoryPreviewDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::convertToDto)
                .toList();
    }
}
