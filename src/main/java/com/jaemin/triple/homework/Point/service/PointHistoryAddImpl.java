package com.jaemin.triple.homework.Point.service;

import com.jaemin.triple.homework.Point.entity.Point;
import com.jaemin.triple.homework.Point.entity.PointType;
import com.jaemin.triple.homework.Point.repository.PointRepository;
import com.jaemin.triple.homework.Review.entity.Review;
import com.jaemin.triple.homework.Review.repository.ReviewRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PointHistoryAddImpl implements PointHistoryCreatedByReviewInterface{

    private final ReviewRepositoryCustom reviewRepositoryCustom;
    private final PointRepository pointRepository;

    @Transactional
    @Override
    public void execPointOperation(Review review) {

        // 일반 점수 계산
        Point point = calculatePoint(review);
        // 보너스 점수 계산
        Point bonusPoint = calculateBonusPoint(review);

        pointRepository.saveAll(Arrays.asList(point, bonusPoint));
    }

    /**
     * 일반 포인트 점수 계산
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/14
     **/
    private Point calculatePoint(Review review) {
        int point = 0;
        if (StringUtils.hasText(review.getContent())) {
            point += 1;
            if (review.getImages().size() > 0) {
                point += 1;
            }
        }

        return Point.of(point, PointType.CONTENT, UUID.randomUUID().toString(), review.getUserId(), review);
    }

    /**
     * 보너스 포인트 점수 계산
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/14
    **/
    public Point calculateBonusPoint(Review review) {
        int bonusPoint = reviewRepositoryCustom.isFirstReviewOnPlace(review.getReviewId()) ? 0 : 1;

        return Point.of(bonusPoint, PointType.BONUS, UUID.randomUUID().toString(), review.getUserId(), review);
    }

}
