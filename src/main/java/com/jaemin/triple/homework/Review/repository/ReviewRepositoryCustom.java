package com.jaemin.triple.homework.Review.repository;

import com.jaemin.triple.homework.Review.entity.QReview;
import com.jaemin.triple.homework.Review.entity.Review;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.jaemin.triple.homework.Place.entity.QPlace.place;
import static com.jaemin.triple.homework.Review.entity.QReview.review;
import static com.jaemin.triple.homework.User.entity.QUser.user;


@Slf4j
@RequiredArgsConstructor
@Repository
public class ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * 사용자가 특정 장소에 1개의 리뷰만 작성했는지 검증
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/04
    **/
    public boolean existsReview(String findUserId, String placeId) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(review)
                .innerJoin(review.placeId)
                .innerJoin(review.userId)
                .where(
                        review.userId.userId.eq(findUserId),
                        review.placeId.placeId.eq(placeId)
                )
                .fetchFirst();

        return fetchOne != null;
    }

    /**
     * 장소에 첫 댓글인지 검증
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/05
     **/
    public boolean existsPlace(String placeId) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(review, review)
                .where(
                        review.placeId.placeId.eq(placeId)
                ).fetchFirst();

        return fetchOne != null;
    }

    /**
     * 내가 작성한 댓글을 제외하곤 아무것도 없는지 검증
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/05
     **/
    public boolean existsPlaceExcludeMe(String placeId, String userId) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(review, review)
                .innerJoin(review.placeId, place)
                .innerJoin(review.userId, user)
                .where(
                        review.placeId.placeId.eq(placeId),
                        review.userId.userId.notIn(userId)
                ).fetchFirst();

        return fetchOne != null;
    }

    /**
     * 해당 장소에 최초 댓글인지 판단
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/14
    **/
    public boolean isFirstReviewOnPlace(String reviewId) {
        String fetchFirst = queryFactory
                .select(review.reviewId)
                .from(review)
                .where(review.placeId.placeId.eq(JPAExpressions.select(review.placeId.placeId)
                                .from(review)
                                .where(review.reviewId.eq(reviewId)))
                        .and(review.reviewId.ne(reviewId))
                )
                .fetchFirst();

        return fetchFirst != null;
    }

}
