package dev.trifonov.user_service.service.api;

import dev.trifonov.user_service.dto.UserDetailsDto;

public interface UserService {
    UserDetailsDto getUserById(long userId);
    UserDetailsDto createUser(UserDetailsDto dto);
    UserDetailsDto updateUser(long userId, UserDetailsDto dto);
    void deleteUser(long userId);
}
