package com.jaemin.triple.homework.Point.service;

import com.jaemin.triple.homework.Point.entity.Point;
import com.jaemin.triple.homework.Point.entity.PointType;
import com.jaemin.triple.homework.Point.repository.PointRepository;
import com.jaemin.triple.homework.Review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PointHistoryDeleteImpl implements PointHistoryCreatedByReviewInterface{

    private final PointRepository pointRepository;

    @Transactional
    @Override
    public void execPointOperation(Review review) {

        // Point 삭제 처리
        long totalPoint = pointRepository.selectTotals(review.getId());

        Point deletePoint = Point.of(-totalPoint, PointType.DELETE, UUID.randomUUID().toString(), review.getUserId(), review);
        pointRepository.save(deletePoint);
    }
}
