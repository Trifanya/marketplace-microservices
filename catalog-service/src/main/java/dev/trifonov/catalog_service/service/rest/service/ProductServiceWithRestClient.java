package dev.trifonov.catalog_service.service.rest.service;

import dev.trifonov.catalog_service.dto.send_dto.NewFavoriteProduct;

public interface ProductServiceWithRestClient {
    String addToCart(NewFavoriteProduct product);
}
