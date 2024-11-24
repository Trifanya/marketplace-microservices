package dev.trifonov.catalog_service.mapper;

import dev.trifonov.catalog_service.entity.Category;
import dev.trifonov.catalog_service.dto.get_only_dto.CategoryDetailsDto;
import dev.trifonov.catalog_service.dto.send_only_dto.CategoryPreviewDto;
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
