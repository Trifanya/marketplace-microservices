package dev.trifonov.user_service.controller;

import dev.trifonov.user_service.dto.UserDetailsDto;
import dev.trifonov.user_service.service.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public UserDetailsDto getUser(long userId) {
        log.info("Принят запрос на получение пользователя {}", userId);
        return userService.getUserById(userId);
    }

    @Override
    public UserDetailsDto createUser(UserDetailsDto dto) {
        log.info("Принят запрос на создание пользователя. dto: {}", dto);
        return userService.createUser(dto);
    }

    @Override
    public UserDetailsDto updateUser(long userId, UserDetailsDto dto) {
        log.info("Принят запрос на обновление пользователя. userId: {}, dto: {}", userId, dto);
        return userService.updateUser(userId, dto);
    }

    @Override
    public ResponseEntity<?> removeUser(long userId) {
        log.info("Принят запрос на удаление пользователя. userId: {}", userId);
        userService.deleteUser(userId);
        return ResponseEntity.ok("Пользователь успешно удален.");
    }

}
