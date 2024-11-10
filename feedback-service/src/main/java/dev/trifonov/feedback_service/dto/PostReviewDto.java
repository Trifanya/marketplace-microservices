package dev.trifonov.feedback_service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PostReviewDto {
    private long userId;
    private long productId;
    private String text;
    private int productQuality;
    private List<String> imageUrls = new ArrayList<>();

    @Override
    public String toString() {
        return "PostReviewDto{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", text='" + text + '\'' +
                ", productQuality=" + productQuality +
                ", imageUrls=" + imageUrls +
                '}';
    }
}
