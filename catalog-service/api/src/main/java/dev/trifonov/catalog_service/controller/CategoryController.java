package dev.trifonov.catalog_service.controller;

import dev.trifonov.catalog_service.dto.send_only_dto.CategoryPreviewDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/catalog")
public interface CategoryController {

    @GetMapping("/categories")
    List<CategoryPreviewDto> getCategories();
}
