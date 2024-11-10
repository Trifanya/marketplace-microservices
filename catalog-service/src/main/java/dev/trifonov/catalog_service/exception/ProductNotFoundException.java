package dev.trifonov.catalog_service.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Товар не найден.");
    }
}
