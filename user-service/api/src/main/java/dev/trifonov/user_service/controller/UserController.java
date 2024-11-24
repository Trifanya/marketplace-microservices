package dev.trifonov.user_service.controller;

import dev.trifonov.user_service.dto.UserDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
public interface UserController {

    @GetMapping("/{userId}")
    UserDetailsDto getUser(@PathVariable long userId);

    @PostMapping
    UserDetailsDto createUser(@RequestBody UserDetailsDto dto);

    @PutMapping("/update/{userId}")
    UserDetailsDto updateUser(@PathVariable long userId, @RequestBody UserDetailsDto dto);

    @DeleteMapping("/{userId}")
    ResponseEntity<?> removeUser(@PathVariable long userId);
}
