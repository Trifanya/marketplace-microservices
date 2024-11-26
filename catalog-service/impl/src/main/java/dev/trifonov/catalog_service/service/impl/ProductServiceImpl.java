package dev.trifonov.catalog_service.service.impl;

import com.google.common.collect.Lists;
import dev.trifonov.catalog_service.entity.Product;
import dev.trifonov.catalog_service.exception.ProductNotFoundException;
import dev.trifonov.catalog_service.mapper.ProductMapper;
import dev.trifonov.catalog_service.repository.ProductRepository;
import dev.trifonov.catalog_service.dto.ProductPreviewDto;
import dev.trifonov.catalog_service.service.api.ProductService;
import dev.trifonov.catalog_service.service.specification.ProductSpecificationConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductPreviewDto getProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        return productMapper.convertToDto(product);
    }

    @Override
    @Transactional
    public List<ProductPreviewDto> getProducts(List<Long> productIds) {
        return Lists.newArrayList(productRepository.findAllById(new HashSet<>(productIds))).stream()
                .map(productMapper::convertToDto)
                .toList();
    }

    @Override
    @Transactional
    public List<ProductPreviewDto> getProductPage(Pageable pageRequest, Map<String, String> filters) {
        Specification<Product> spec = ProductSpecificationConstructor.build(filters);
        return productRepository.findAll(spec, pageRequest)
                .getContent().stream()
                .map(productMapper::convertToDto)
                .toList();
    }

    @Override
    @Transactional
    public void recalculateRating(long productId, float recalculatedRating) {
        Product productToUpdate = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        productToUpdate.setRating(recalculatedRating);
        productRepository.save(productToUpdate);
    }
}
