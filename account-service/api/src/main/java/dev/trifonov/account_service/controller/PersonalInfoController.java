package dev.trifonov.account_service.controller;

import dev.trifonov.user_service.dto.UserDetailsDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/account/{userId}")
public interface PersonalInfoController {

    @GetMapping("/info")
    UserDetailsDto getUserInfo(@PathVariable long userId);

    @PutMapping("/update")
    UserDetailsDto updateUserInfo(@RequestBody UserDetailsDto dto);
}
