package dev.trifonov.account_service.service.rest.service;

import dev.trifonov.account_service.dto.ProductPreview;

import java.util.List;

public interface ProductServiceWithRestClient {
    List<ProductPreview> getProducts(List<Integer> productIds);
}
