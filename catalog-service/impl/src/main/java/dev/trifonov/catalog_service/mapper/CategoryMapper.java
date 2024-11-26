package dev.trifonov.catalog_service.mapper;

import dev.trifonov.catalog_service.entity.Category;
import dev.trifonov.catalog_service.dto.CategoryDetailsDto;
import dev.trifonov.catalog_service.dto.CategoryPreviewDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper implements AbstractMapper<Category, CategoryDetailsDto, CategoryPreviewDto> {

    private final ModelMapper modelMapper;

    @Override
    public CategoryPreviewDto convertToDto(Category entityInstance) {
        return modelMapper.map(entityInstance, CategoryPreviewDto.class);
    }

    @Override
    public Category convertToEntity(CategoryDetailsDto dtoInstance) {
        return modelMapper.map(dtoInstance, Category.class);
    }
}
