package com.jaemin.triple.homework.Review.entity;

import com.jaemin.triple.homework.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Images extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String attachedPhotoId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    /** 생성 메소드 **/
    public static Images of(String attachedPhotoId) {
        return Images.builder()
                .attachedPhotoId(attachedPhotoId)
                .build();
    }

    /** 생성 메소드 **/
    public static Images of(String attachedPhotoId, Review review) {
        return Images.builder()
                .attachedPhotoId(attachedPhotoId)
                .review(review)
                .build();
    }

    /** 연관 관계 **/
    public void setReview(Review review) {
        this.review = review;
    }
}
