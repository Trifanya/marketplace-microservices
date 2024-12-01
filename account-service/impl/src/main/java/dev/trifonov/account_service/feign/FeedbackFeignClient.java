package dev.trifonov.account_service.feign;

import dev.trifonov.feedback_service.dto.ReviewDetailsDto;
import dev.trifonov.feedback_service.dto.ReviewPreviewDto;
import jakarta.persistence.GeneratedValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@FeignClient(value = "feedback-service"/*, url = "${feign.feedback-service.uri}"*/)
public interface FeedbackFeignClient {

    @GetMapping("/reviews")
    List<ReviewPreviewDto> getReviewsByIds(Set<Long> ids);

}
