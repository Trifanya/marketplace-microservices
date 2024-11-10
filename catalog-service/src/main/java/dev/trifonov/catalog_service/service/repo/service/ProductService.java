package dev.trifonov.catalog_service.service.repo.service;

import dev.trifonov.catalog_service.dto.send_dto.ProductPreviewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {
    ProductPreviewDto getProduct(Long productId) throws InterruptedException;
    List<ProductPreviewDto> getProducts(Set<Integer> productIds);
    List<ProductPreviewDto> getProducts(Pageable pageRequest, Map<String, String> filters);
    void recalculateRating(long productId, float reacalculatedRating);
}
