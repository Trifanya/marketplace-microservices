package dev.trifonov.account_service.service.rest.service;

import dev.trifonov.account_service.dto.ReviewDto;

import java.util.List;

public interface UserReviewServiceWithRestClient {
    List<ReviewDto> getReviews(List<Integer> reviewIds);

}
