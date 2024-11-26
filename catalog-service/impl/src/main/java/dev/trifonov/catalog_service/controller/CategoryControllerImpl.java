package dev.trifonov.catalog_service.controller;

import dev.trifonov.catalog_service.dto.CategoryPreviewDto;
import dev.trifonov.catalog_service.service.api.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    public List<CategoryPreviewDto> getCategories() {
        log.info("Принят запрос на получение списка категорий.");
        return categoryService.getAllCategories();
    }

}
