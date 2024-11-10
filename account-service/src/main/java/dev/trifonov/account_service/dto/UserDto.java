package dev.trifonov.account_service.dto;

public record UserDto(
        Integer id,
        String name,
        String surname,
        String email
) {
}
