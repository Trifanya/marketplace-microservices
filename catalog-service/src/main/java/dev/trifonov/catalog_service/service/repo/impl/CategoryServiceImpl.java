package dev.trifonov.catalog_service.service.repo.impl;

import dev.trifonov.catalog_service.entity.Category;
import dev.trifonov.catalog_service.repository.CategoryRepository;
import dev.trifonov.catalog_service.service.repo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
