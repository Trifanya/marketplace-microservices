package dev.trifonov.catalog_service.mapper;

import dev.trifonov.catalog_service.dto.CharacteristicDto;
import dev.trifonov.catalog_service.entity.Product;
import dev.trifonov.catalog_service.entity.Characteristic;
import dev.trifonov.catalog_service.entity.ProductImage;
import dev.trifonov.catalog_service.dto.ProductDetailsDto;
import dev.trifonov.catalog_service.dto.ProductPreviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductMapper implements AbstractMapper<Product, ProductDetailsDto, ProductPreviewDto> {
    private final ModelMapper modelMapper;
    private final AbstractMapper<Characteristic, CharacteristicDto, CharacteristicDto> pCharMapper;

    @Override
    public ProductPreviewDto convertToDto(Product product) {
        ProductPreviewDto dto = modelMapper.map(product, ProductPreviewDto.class);
        dto.setProductCoverUrl(
                product.getProductImages().stream()
                        .map(ProductImage::getUrl)
                        .findFirst().orElse("defaultUrl"));
        return dto;
    }

    @Override
    public Product convertToEntity(ProductDetailsDto dto) {
        Product product = modelMapper.map(dto, Product.class);
        product.setCharacteristics(
                dto.getCharacteristics().stream()
                        .map(pCharMapper::convertToEntity)
                        .toList());
        return product;
    }
}
