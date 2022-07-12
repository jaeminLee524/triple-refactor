package com.jaemin.triple.homework.domain.review.repository;

import com.jaemin.triple.homework.Place.entity.Place;
import com.jaemin.triple.homework.Review.entity.Images;
import com.jaemin.triple.homework.Review.entity.Review;
import com.jaemin.triple.homework.User.entity.User;
import com.jaemin.triple.homework.Place.repository.PlaceRepository;
import com.jaemin.triple.homework.Review.repository.ReviewRepository;
import com.jaemin.triple.homework.User.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles(profiles = "test-container")
@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {

    }

    @DisplayName("리뷰 생성")
    @Test
    void createReview() {

        //given
        User user = User.of("testA@naver.com", "testA", "12345678", UUID.randomUUID().toString());

        Place place = Place.of("시그니엘 호텔", UUID.randomUUID().toString());

        Images images1 = Images.of(UUID.randomUUID().toString());
        Images images2 = Images.of(UUID.randomUUID().toString());
        Review review = Review.of("좋아요!", UUID.randomUUID().toString(), user, place, Arrays.asList(images1, images2));

        //when
        userRepository.save(user);
        placeRepository.save(place);
        reviewRepository.save(review);

        //then
        /** DB에 저장됐는지만 확인하면 됨 **/
        assertThat(user.getId()).isNotNull();
        assertThat(place.getId()).isNotNull();
        assertThat(images1.getId()).isNotNull();
        assertThat(images2.getId()).isNotNull();

        /** 리뷰 저장 값 확인 **/
        assertThat(review.getId()).isNotNull();
        assertThat(review.getImages().size()).isEqualTo(2);
    }

    @DisplayName("리뷰 수정 - 콘텐츠 수정")
    @Test
    void modifyReview() {
        //given
        User user = User.of("testB@naver.com", "testB", "12345678", UUID.randomUUID().toString());

        Place place = Place.of("제주 신라 호텔", UUID.randomUUID().toString());

        userRepository.save(user);
        placeRepository.save(place);

        Images images1 = Images.of(UUID.randomUUID().toString());
        Images images2 = Images.of(UUID.randomUUID().toString());
        Review review = Review.of("좋아요!", UUID.randomUUID().toString(), user, place, Arrays.asList(images1, images2));

        reviewRepository.save(review);

        //when
        review.setContent("dirty!!!!!!!!!!!");

        //then
        assertThat(review.getContent()).isEqualTo("dirty!!!!!!!!!!!");
    }

}