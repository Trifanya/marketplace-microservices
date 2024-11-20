package dev.trifonov.user_service.controller;

import dev.trifonov.user_service.entity.MarketplaceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MarketplaceUserController {

    @GetMapping("/{id}")
    public String getUserInfo() {
        return "";
    }

    @PostMapping("/sign-up")
    public MarketplaceUser signUp() {
        return new MarketplaceUser();
    }

}
