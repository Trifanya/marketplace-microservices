package dev.trifonov.catalog_service.mapper;

import dev.trifonov.catalog_service.dto.CharacteristicDto;
import dev.trifonov.catalog_service.entity.Characteristic;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCharacteristicMapper implements AbstractMapper<Characteristic, CharacteristicDto, CharacteristicDto> {

    private final ModelMapper modelMapper;

    private final CategoryCharacteristicMapper cCharMapper;

    @Override
    public CharacteristicDto convertToDto(Characteristic entityInstance) {
        throw new RuntimeException("Маппинг не реализован.");
    }

    @Override
    public Characteristic convertToEntity(CharacteristicDto dto) {
        Characteristic pc = modelMapper.map(dto, Characteristic.class);
        pc.setFeature(cCharMapper.convertToEntity(dto.getCharacteristicDto()));
        return pc;
    }
}
