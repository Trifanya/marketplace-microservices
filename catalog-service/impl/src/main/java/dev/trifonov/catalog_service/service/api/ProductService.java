package dev.trifonov.catalog_service.service.api;

import dev.trifonov.catalog_service.dto.send_only_dto.ProductPreviewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {
    ProductPreviewDto getProduct(long productId);
    List<ProductPreviewDto> getProducts(List<Long> productIds);
    List<ProductPreviewDto> getProductPage(Pageable pageRequest, Map<String, String> filters);
    void recalculateRating(long productId, float reacalculatedRating);
}
