package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.dto.UserDto;
import dev.trifonov.account_service.service.rest.service.UserServiceWithRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/{userId}")
public class PersonalInfoController {

    private final UserServiceWithRestClient userServiceWithRestClient;

    @GetMapping("/accountInfo")
    public UserDto getUserInfo(@PathVariable("userId") Integer userId) {
        log.trace("userId = " + userId);
        return userServiceWithRestClient.getUserInfo(userId);
    }
}
