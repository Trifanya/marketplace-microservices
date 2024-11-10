package dev.trifonov.account_service.service.rest.service;

import dev.trifonov.account_service.dto.UserDto;

public interface UserServiceWithRestClient {
    UserDto getUserInfo(Integer userId);
}
