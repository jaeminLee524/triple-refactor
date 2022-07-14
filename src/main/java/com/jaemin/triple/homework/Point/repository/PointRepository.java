package com.jaemin.triple.homework.Point.repository;

import com.jaemin.triple.homework.Point.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PointRepository extends JpaRepository<Point, Long> {

    @Query("SELECT SUM(p.point) FROM Point p WHERE p.reviewId.id =:review_id")
    long selectTotals(@Param("review_id") Long review_id);
}
