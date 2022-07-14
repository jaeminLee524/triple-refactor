package com.jaemin.triple.homework.Point.repository;

import com.jaemin.triple.homework.Point.entity.Point;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jaemin.triple.homework.Point.entity.QPoint.point1;


@Slf4j
@RequiredArgsConstructor
@Repository
public class PointRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * 사용자 별 포인트 내역 조회
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/07
    **/
    public Page<Point> getPointHistory(Pageable pageable, String userId) {
        // 카운트 쿼리
        Long count = queryFactory
                .select(point1.countDistinct())
                .from(point1)
                .where(
                        point1.userId.userId.eq(userId)
                ).fetchOne();

        // 데이터 쿼리
        List<Point> content = queryFactory
                .selectFrom(point1)
                .where(point1.userId.userId.eq(userId))
                .orderBy(point1.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .distinct()
                .fetch();

        return PageableExecutionUtils.getPage(content, pageable, () -> count);
    }

    /**
     * 사용자 별 포인트 총점 조회
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/07
     **/
    public long getPointSum(String userId) {
        Long sum = queryFactory
                .select(point1.point.sum().coalesce(0L))
                .from(point1)
                .where(point1.userId.userId.eq(userId))
                .fetchOne();

        return sum;
    }

}
