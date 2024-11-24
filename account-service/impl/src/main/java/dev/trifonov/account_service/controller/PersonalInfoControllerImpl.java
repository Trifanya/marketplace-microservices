package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.feign.UserFeignClient;
import dev.trifonov.user_service.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PersonalInfoControllerImpl implements PersonalInfoController {

    private final UserFeignClient userFeignClient;

    @Override
    public UserDetailsDto getUserInfo(long userId) {
        log.info("Принят запрос на получение личных данных пользователя. userId: {}", userId);
        return userFeignClient.getUserInfo(userId);
    }

    @Override
    public UserDetailsDto updateUserInfo(UserDetailsDto dto) {
        log.info("Принят запрос на обновление личных данных пользователя. userDetailsDto: {}", dto);
        return userFeignClient.updateUserInfo(dto);
    }
}
