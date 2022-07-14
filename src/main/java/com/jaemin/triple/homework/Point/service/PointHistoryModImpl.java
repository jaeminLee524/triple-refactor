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
public class PointHistoryModImpl implements PointHistoryCreatedByReviewInterface{

    private final PointRepository pointRepository;

    @Transactional
    @Override
    public void execPointOperation(Review review) {
//        Point plusPoint;

        /*if (isMod) {
            // point +1
            plusPoint = Point.of(1, PointType.CONTENT, UUID.randomUUID().toString(), review.getUserId(), review);
        }else {
            // point -1
            plusPoint = Point.of(-1, PointType.CONTENT, UUID.randomUUID().toString(), review.getUserId(), review);
        }

        pointRepository.save(plusPoint);*/
    }
}
