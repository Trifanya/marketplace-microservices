package dev.trifonov.account_service.service.rest.impl;

import dev.trifonov.account_service.dto.UserDto;
import dev.trifonov.account_service.service.rest.service.UserServiceWithRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceWithRestClientImpl implements UserServiceWithRestClient {

    public final RestClient restClient;

    @Override
    public UserDto getUserInfo(Integer userId) {
        log.info("");
        return restClient
                .get()
                .uri("localhost:8765/user-service/users")
                .retrieve()
                .toEntity(UserDto.class)
                .getBody();
    }
}
