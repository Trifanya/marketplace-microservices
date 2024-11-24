package dev.trifonov.user_service.mapper;

public interface AbstractMapper<E, D, P> {
    P convertToPreviewDto(E entityInstance);
    D convertToDetailsDto(E entityInstance);
    E convertToEntity(D dtoInstance);
}
