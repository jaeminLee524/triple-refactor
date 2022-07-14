package com.jaemin.triple.homework.Review.entity;

import com.jaemin.triple.homework.global.entity.BaseEntity;
import com.jaemin.triple.homework.Place.entity.Place;
import com.jaemin.triple.homework.User.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Review extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @Column(nullable = false, unique = true)
    private String reviewId;

    /** 연관관계 **/
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "place_id")
    private Place placeId;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Images> images = new ArrayList<>();

    public Review(String content, String reviewId, User userId, Place placeId, List<Images> images) {
        this.content = content;
        this.reviewId = reviewId;
        this.userId = userId;
        this.placeId = placeId;
        for (Images image : images) {
            this.images.add(image);
            image.setReview(this);
        }
    }

    /** 생성 메소드 **/
    public static Review of(String content, String reviewId, User user, Place place, List<Images> images) {
        return new Review(content, reviewId, user, place, images);
//        Review review = new Review();
//        review.content = content;
//        review.reviewId = reviewId;
//        review.userId = user;
//        review.placeId = place;

//        for (Images image : images) {
//            review.images.add(image);
//            image.setReview(review);
//        }

//        return review;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateImages(List<Images> images) {
        this.images.clear();
        for (Images image : images) {
            this.images.add(image);
            image.setReview(this);
        }
    }

}
