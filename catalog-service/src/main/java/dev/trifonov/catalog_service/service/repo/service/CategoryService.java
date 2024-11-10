package dev.trifonov.catalog_service.service.repo.service;

import dev.trifonov.catalog_service.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
}
