package dev.trifonov.feedback_service.mapper;

import dev.trifonov.feedback_service.dto.PostReviewDto;
import dev.trifonov.feedback_service.entity.Image;
import dev.trifonov.feedback_service.entity.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewMapper {

    private final ModelMapper modelMapper;

    public Review convertToReview(PostReviewDto dto) {
        log.info("dto: {}", dto);
        Review review = modelMapper.map(dto, Review.class);
        review.setImages(dto.getImageUrls().stream()
                .map(url -> new Image().setUrl(url))
                .toList());
        log.info("review: {}", review);
        return review;
    }

    public PostReviewDto convertToDto(Review review) {
        log.info("review: {}", review);
        PostReviewDto dto = modelMapper.map(review, PostReviewDto.class);
        dto.setImageUrls(
                review.getImages().stream()
                        .map(Image::getUrl)
                        .toList()
        );
        log.info("dto: {}", dto);
        return dto;
    }
}
