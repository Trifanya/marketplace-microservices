package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.feign.UserFeignClient;
import dev.trifonov.user_service.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/{userId}")
public class PersonalInfoController {

    private final UserFeignClient userFeignClient;
    //private final UserServiceWithRestClient userServiceWithRestClient;

    @GetMapping("/info")
    public UserDetailsDto getUserInfo(@PathVariable("userId") long userId) {
        log.info("Принят запрос на получение личных данных пользователя. userId: {}", userId);
        return userFeignClient.getUserInfo(userId);
    }

    @PutMapping("/update")
    public UserDetailsDto updateUserInfo(@RequestBody UserDetailsDto userDetailsDto) {
        log.info("Принят запрос на обновление личных данных пользователя. userDetailsDto: {}", userDetailsDto);
        return userFeignClient.updateUserInfo(userDetailsDto);
    }
}
