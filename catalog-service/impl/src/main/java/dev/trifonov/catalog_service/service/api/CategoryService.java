package dev.trifonov.catalog_service.service.api;

import dev.trifonov.catalog_service.dto.send_only_dto.CategoryPreviewDto;

import java.util.List;

public interface CategoryService {
    List<CategoryPreviewDto> getAllCategories();
}
