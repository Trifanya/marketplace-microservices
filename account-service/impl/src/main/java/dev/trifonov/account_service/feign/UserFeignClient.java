package dev.trifonov.account_service.feign;

import dev.trifonov.user_service.dto.UserDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "user-service"/*, url = "${feign.user-service.uri}"*/)
public interface UserFeignClient {

    @GetMapping("{userId}")
    UserDetailsDto getUserInfo(@PathVariable("userId") long userId);

    @PutMapping("/update")
    UserDetailsDto updateUserInfo(UserDetailsDto dto);
}
