package dev.trifonov.catalog_service.mapper;

public interface AbstractMapper<E, D, P> {
    P convertToDto(E entityInstance);
    E convertToEntity(D dtoInstance);
}
