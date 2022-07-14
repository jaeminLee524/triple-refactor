package com.jaemin.triple.homework.Point.service;

import com.jaemin.triple.homework.Point.entity.Point;
import com.jaemin.triple.homework.Review.entity.Review;
import com.jaemin.triple.homework.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jaemin.triple.homework.Point.entity.Point.EventType.*;

/**
 * 퍼사드 패턴 적용
 * @author jaemin
 * @version 1.0.0
 * 작성일 2022/07/14
 **/

@RequiredArgsConstructor
@Service
public class PointEventFacade {

    private final PointHistoryAddImpl pointHistoryAdd;
    private final PointHistoryModImpl pointHistoryMod;
    private final PointHistoryDeleteImpl pointHistoryDelete;

    // ADD, MOD, DELETE의 서브 클래스 동작
    @Transactional
    public void event(Point.EventType eventType, Review review) {
        if (eventType.isType()) {
            grantPointByEvent(eventType, review);
            return;
        }
        throw new RuntimeException("잘못된 요청입니다.");
    }

    private void grantPointByEvent(Point.EventType eventType, Review review) {
        switch (eventType) {
            case REVIEW_ADD:
                pointHistoryAdd.execPointOperation(review);
                return;

            case REVIEW_MOD:
                pointHistoryMod.execPointOperation(review);
                return;

            case REVIEW_DELETE:
                pointHistoryDelete.execPointOperation(review);
                return;

            default:
                throw new RuntimeException("잘못된 요청입니다.");
        }
    }

}
