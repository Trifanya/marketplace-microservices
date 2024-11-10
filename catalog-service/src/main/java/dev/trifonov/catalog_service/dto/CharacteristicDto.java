package dev.trifonov.catalog_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacteristicDto {
    private String value;
    private FeatureDto characteristicDto;
}
