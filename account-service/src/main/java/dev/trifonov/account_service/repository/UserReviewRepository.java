package dev.trifonov.account_service.repository;

import dev.trifonov.account_service.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReviewRepository extends JpaRepository<UserReview, Integer> {
    List<UserReview> findAllByUserId(Integer userId);
}
