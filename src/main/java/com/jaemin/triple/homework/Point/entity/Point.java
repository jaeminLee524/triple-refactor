package com.jaemin.triple.homework.Point.entity;

import com.jaemin.triple.homework.Review.entity.Review;
import com.jaemin.triple.homework.User.entity.User;
import com.jaemin.triple.homework.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Point extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long point;
    @Enumerated(EnumType.STRING)
    private PointType pointType;
    @Column(nullable = false, unique = true)
    private String pointId;

    /** 연관관계 **/
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "review_id")
    private Review reviewId;

    /** 생성 메소드 **/
    public static Point of(long point, PointType pointType, String pointId, User findUser, Review review) {
        return Point.builder()
                .point(point)
                .pointType(pointType)
                .pointId(pointId)
                .userId(findUser)
                .reviewId(review)
                .build();
    }
}
