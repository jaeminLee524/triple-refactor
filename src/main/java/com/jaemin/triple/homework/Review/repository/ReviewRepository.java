package com.jaemin.triple.homework.Review.repository;

import com.jaemin.triple.homework.Review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReviewId(@Param("reviewId") String reviewId);
}
