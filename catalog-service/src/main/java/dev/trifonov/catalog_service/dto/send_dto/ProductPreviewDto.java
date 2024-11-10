package dev.trifonov.catalog_service.dto.send_dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPreviewDto {
        private Integer id;
        private String name;
        private String brand;
        private Integer price;
        private Double rating;
        private String coverImageUrl;
}
