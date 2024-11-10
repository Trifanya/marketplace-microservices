package dev.trifonov.feedback_service.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ShowReviewDto {
    private Long userId;
    private String text;
    private Integer productQuality;
    private List<String> imageUrls;
    private LocalDateTime postedAt;
}
