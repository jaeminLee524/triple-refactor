package com.jaemin.triple.homework.Review.entity;

import com.jaemin.triple.homework.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

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

    public Images(String attachedPhotoId) {
        this.attachedPhotoId = attachedPhotoId;
    }

    public Images(String attachedPhotoId, Review review) {
        this.attachedPhotoId = attachedPhotoId;
        this.review = review;
    }

    /** 생성 메소드 **/
    public static Images of(String attachedPhotoId) {
        return new Images(attachedPhotoId);
    }

    /** 생성 메소드 **/
    public static Images of(String attachedPhotoId, Review review) {
        return new Images(attachedPhotoId, review);
    }


    /** 연관 관계 **/
    public void setReview(Review review) {
        this.review = review;
    }
}
