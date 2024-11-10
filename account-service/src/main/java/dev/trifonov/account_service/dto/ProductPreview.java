package dev.trifonov.account_service.dto;

public record ProductPreview(
        Integer id,
        String name,
        String brand,
        Integer price,
        Double rating,
        String imageUrl
) {
}
