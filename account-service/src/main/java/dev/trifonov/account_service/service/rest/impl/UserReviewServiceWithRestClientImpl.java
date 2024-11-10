package dev.trifonov.account_service.service.rest.impl;

import dev.trifonov.account_service.dto.ReviewDto;
import dev.trifonov.account_service.service.rest.service.UserReviewServiceWithRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserReviewServiceWithRestClientImpl implements UserReviewServiceWithRestClient {

    public final RestClient restClient;

    @Override
    public List<ReviewDto> getReviews(List<Integer> reviewIds) {
        log.info("");
        return restClient
                .get()
                .uri("localhost:8765/feedback-service/feedback")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
