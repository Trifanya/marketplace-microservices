package dev.trifonov.feedback_service.repository;

import dev.trifonov.feedback_service.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "SELECT AVG(review.productQuality) " +
            "FROM Review as review " +
            "WHERE review.productId = :productId")
    float calculateAvgRatingByProductId(long productId);

    List<Review> findAllByProductId(long productId);

    List<Review> findAllByIdIn(List<Long> ids);
}
