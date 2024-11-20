package dev.trifonov.catalog_service.mapper;

import dev.trifonov.catalog_service.dto.FeatureDto;
import dev.trifonov.catalog_service.entity.Feature;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCharacteristicMapper implements AbstractMapper<Feature, FeatureDto, FeatureDto> {

    private final ModelMapper modelMapper;

    @Override
    public FeatureDto convertToDto(Feature entityInstance) {
        return modelMapper.map(entityInstance, FeatureDto.class);
    }

    @Override
    public Feature convertToEntity(FeatureDto dtoInstance) {
        return modelMapper.map(dtoInstance, Feature.class);
    }
}
