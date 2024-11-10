package dev.trifonov.user_service.controller;

import dev.trifonov.user_service.entity.MarketplaceUser;
import dev.trifonov.user_service.service.MarketplaceUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MarketplaceUserController {

    private final UserDetailsService userDetailsService;

    @GetMapping("/{id}")
    public String getUser() {
        return "";
    }

    @PostMapping("/sign-up")
    public MarketplaceUser signUp() {
        return new MarketplaceUser();
    }

}
