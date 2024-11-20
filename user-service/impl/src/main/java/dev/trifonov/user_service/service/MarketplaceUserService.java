package dev.trifonov.user_service.service;

import dev.trifonov.user_service.controller.dto.NewUserDto;
import dev.trifonov.user_service.entity.MarketplaceUser;

public interface MarketplaceUserService {
    MarketplaceUser findUser(int userId);
    MarketplaceUser createUser(NewUserDto userDto);
    void deleteUser(int userId);
}
