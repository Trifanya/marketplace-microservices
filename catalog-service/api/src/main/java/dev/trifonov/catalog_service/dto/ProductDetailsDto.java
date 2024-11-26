package dev.trifonov.catalog_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDetailsDto {
    private String name;
    private String brand;
    private String seller;
    private Integer price;
    private Integer quantity;
    private String description;
    private Long categoryId;
    private List<CharacteristicDto> characteristics;
    private List<String> imageUrls;
}
